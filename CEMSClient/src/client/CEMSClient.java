package client;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import common.ActiveTest;
import common.Course;
import common.EmptyUser;
import common.FinishedTest;
import common.Principle;
import common.Question;
import common.Report;
import common.ScheduledTest;
import common.Student;
import common.StudentGrade;
import common.Teacher;
import common.Test;
import common.TestFile;
import common.TimeExtensionRequest;
import common.User;
import javafx.util.Pair;
import ocsf.client.ObservableClient;

public class CEMSClient extends ObservableClient {
	private User activeUser;
	private ClientController controller;
	private static boolean awaitResponse = false;

	public User getActiveUser() {
		return activeUser;
	}

	public CEMSClient(String host, int port, ClientController controller) {
		super(host, port);
		this.controller = controller;
	}

	/**
	 * receives message from the UI and sends it to the server
	 * 
	 * @param msg
	 * @throws Exception
	 */
	public void handleMessageFromClientUI(String msg) throws Exception {
		openConnection();
		if (msg.equals("SIGN_OUT")) {
			closeConnection();
			return;
		}
		if (msg.startsWith("FILE")) {
			try {
				String[] split = msg.split("~");
				String[] fileNameSplit = split[0].split("-");
				if (fileNameSplit[1].length() != 0) {
					TestFile file = new TestFile(fileNameSplit[1].replace("\\", "/"));
					File f = new File((fileNameSplit[1].replace("\\", "/")).substring(1,
							fileNameSplit[1].replace("\\", "/").length() - 1));
					byte[] byteArray = new byte[(int) f.length()];
					FileInputStream fis = new FileInputStream(f);
					BufferedInputStream bis = new BufferedInputStream(fis);
					file.initArray(byteArray.length);
					file.setSize(byteArray.length);
					bis.read(file.getByteArray(), 0, byteArray.length);
					String[] args = split[1].split("-");

					System.out.println("split1: " + split[1] + " args1: " + args[1]);
					sendToServer(new Pair<TestFile, String>(file, split[1]));
					bis.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		awaitResponse = true;
		sendToServer(msg);
		while (awaitResponse) {
			Thread.sleep(100);
		}
	}

	@SuppressWarnings("unchecked")
	public void handleMessageFromServer(Object msg) {
		awaitResponse = false;
		if (msg instanceof EmptyUser)
			ClientController.setRoleFrame("null");
		else if (msg.equals("userAlreadyConnected")) {
			ClientController.setRoleFrame("userAlreadyConnected");
		} else {
			// case of login
			if (msg instanceof User) {
				if (msg instanceof Teacher) {
					ClientController.setRoleFrame("Teacher");
					activeUser = (Teacher) msg;
				} else if (msg instanceof Student) {
					ClientController.setRoleFrame("Student");
					activeUser = (Student) msg;
				} else if (msg instanceof Principle) {
					ClientController.setRoleFrame("Principle");
					activeUser = (Principle) msg;
				}
			} else if (msg instanceof TimeExtensionRequest) {
				System.out.println(((TimeExtensionRequest) msg).getTestCode());
				ClientController.setTimeExtensionRequest((TimeExtensionRequest) msg);
			} else if (msg instanceof Report) {
				ClientController.setReport((Report) msg);
			}
			// case of filling a table
			else if (msg instanceof ArrayList<?>) {
				if (((ArrayList<?>) msg).isEmpty()) {
					ClientController.setActiveTests(null);
					ClientController.setQuestions(null);
					ClientController.setScheduledTests(null);
					ClientController.setTests(null);
					ClientController.setTimeExtensionRequests(null);
				} else if (((ArrayList<?>) msg).get(0) instanceof Pair) {
					System.out.println("yessssssssssssss");
					if (((ArrayList<Pair<?, ?>>) msg).get(0).getValue() instanceof Integer) {
						System.out.println("yes1");
						ClientController.setStudentAnswers((ArrayList<Pair<String, Integer>>) msg);
					} else {
						System.out.println("yes2");
						ClientController.setCopiedStudents((ArrayList<Pair<String, String>>) msg);
					}
				}
				// get questions from questions DB
				else if (((ArrayList<?>) msg).get(0) instanceof Question)
					ClientController.setQuestions((ArrayList<Question>) msg);
				// get tests from test DB
				else if (((ArrayList<?>) msg).get(0) instanceof Test)
					ClientController.setTests((ArrayList<Test>) msg);
				// get students
				else if (((ArrayList<?>) msg).get(0) instanceof Student)
					ClientController.setStudents((ArrayList<Student>) msg);
				// get teachers
				else if (((ArrayList<?>) msg).get(0) instanceof Teacher)
					ClientController.setTeachers((ArrayList<Teacher>) msg);
				// get scheduled tests from scheduled_tests DB
				else if (((ArrayList<?>) msg).get(0) instanceof ScheduledTest) {
					ClientController.setScheduledTests((ArrayList<ScheduledTest>) msg);
				}
				// get active tests from active_tests DB
				else if (((ArrayList<?>) msg).get(0) instanceof ActiveTest)
					ClientController.setActiveTests((ArrayList<ActiveTest>) msg);
				// get finished tests from finished_tests DB
				else if (((ArrayList<?>) msg).get(0) instanceof FinishedTest)
					ClientController.setFinishedTests((ArrayList<FinishedTest>) msg);
				// get courses by field
				else if (((ArrayList<?>) msg).get(0) instanceof Course)
					ClientController.setCourses((ArrayList<Course>) msg);
				// get reports
				else if (((ArrayList<?>) msg).get(0) instanceof Report)
					ClientController.setReports((ArrayList<Report>) msg);
				else if (((ArrayList<?>) msg).get(0) instanceof TimeExtensionRequest)
					ClientController.setTimeExtensionRequests((ArrayList<TimeExtensionRequest>) msg);
				else if (((ArrayList<?>) msg).get(0) instanceof StudentGrade)
					ClientController.setGrades((ArrayList<StudentGrade>) msg);
			} else if (msg instanceof Test) {
				if (null != ((Test) msg)) {
					ClientController.setStudentTest((Test) msg);
				}
			} else if (msg instanceof ScheduledTest)
				ClientController.setScheduledTest((ScheduledTest) msg);
			else if (msg instanceof Course)
				ClientController.setCourse((Course) msg);
			else if (msg instanceof String) {
				String str = (String) msg;
				// getting message from query when adding a new question
				String[] questionAdded = str.split(":");

				if (str.equals("deleted")) {
					ClientController.setTestDeleted(true);
					ClientController.setQuestionDeleted(true);

				} else if (str.equals("reportDeleted")) {
					ClientController.setReportDeleted(true);

				} else if (str.equals("notDeleted")) {
					ClientController.setTestDeleted(false);
					ClientController.setQuestionDeleted(false);

				} else if (str.equals("scheduled"))
					ClientController.setTestScheduled(true);
				else if (str.equals("notScheduled"))
					ClientController.setTestScheduled(false);
				else if (str.equals("testActive")) {
					ClientController.setIsActiveTest(true);
					ClientController.setTimeForTest(true);
				} else if (str.equals("testNotActive"))
					ClientController.setIsActiveTest(false);

				else if (str.equals("testLocked"))
					ClientController.setTestLocked(true);
				else if (str.equals("testNotLocked"))
					ClientController.setTestLocked(false);

				else if (str.equals("lastStudent"))
					ClientController.setLastStudentInTest(true);
				else if (str.equals("notLastStudent"))
					ClientController.setLastStudentInTest(false);

				else if (str.equals("timeForTest"))
					ClientController.setTimeForTest(true);
				else if (str.equals("notTimeForTest"))
					ClientController.setTimeForTest(false);
				else if (str.equals("studentAdded"))
					ClientController.setStudentAddedToTest(true);
				else if (str.equals("studentNotAdded"))
					ClientController.setStudentAddedToTest(false);
				else if (str.equals("studentRemovedFromTest"))
					ClientController.setStudentDeletedFromTest(true);
				else if (str.equals("studentNotRemovedFromTest"))
					ClientController.setStudentDeletedFromTest(false);
				else if (questionAdded[0].equals("questionAdded")) {
					ClientController.setQuestionAdded(true);
					// getting new question ID
					ClientController.setNewQuestionId(questionAdded[1]);
				} else if (str.equals("editSuccess"))
					ClientController.setQuestionEdited(true);
				else if (str.startsWith("testAdded")) {
					String[] tmp = str.split(":");
					ClientController.setId(tmp[1]);
				}

				else if (str.equals("finishedTestUpdated"))
					ClientController.setTestRemoved(true);
				else if (str.equals("finishedTestNotUpdated"))
					ClientController.setTestRemoved(false);

				else if (str.equals("testRemoved"))
					ClientController.setTestRemoved(true);
				else if (str.equals("testNotRemoved"))
					ClientController.setTestRemoved(false);
				else if (str.equals("testRescheduled"))
					ClientController.setTestRescheduled(true);
				else if (str.equals("testNotRescheduled"))
					ClientController.setTestRescheduled(false);
				else if (str.equals("notifyPrinciple")) {
					setChanged();
					notifyObservers(ClientController.getActiveTestController());
				} else if (str.startsWith("notifyTeacher")) {
					String[] splitRes = str.split(":");
					setChanged();
					notifyObservers(splitRes[1]);
				} else if (str.equals("notifyStudent")) {
					setChanged();
					notifyObservers("lockTest");
				} else if (str.startsWith("timeExtension")) {
					setChanged();
					notifyObservers(str);
				} else if (str.equals("studentsNotifiedLocked")) {
					ClientController.setStudentNotified(true);
				} else if (str.equals("principleNotified"))
					ClientController.setPrincipleNotified(true);
				else if (((String) msg).startsWith("name")) {
					// getting author name from query
					String[] toSplit = ((String) msg).split(":");
					ClientController.setAuthorName(toSplit[1]);
				}
			}

		}
	}

}
