package MySpace;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class Request {
	private int id;
	private static int count;
	private Operator operator;
	public Request() {
		id = ++Request.count;
	}
	enum Status { NEW, INPROGRESS, CLOSED }
	private Status status = Status.NEW;
	public void changeStatus(Operator newOperator) {
		operator = newOperator;
		status = Status.INPROGRESS;
	}
	public void closeStatus() {
		status = Status.CLOSED;
	}
	public int getId() {
		return id;
	}
	public Operator getOperator() {
		return operator;
	}
	public Status getStatus() {
		return status;
	}
	@Override
	public String toString() {
		return "Заявка №" + id + ", статус: " + status + ", оператор: " + operator;
	}
}

class Operator {
	private int id;
	private static int count;
	public Operator() {
		id = ++Operator.count;
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Оператор №" + id;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operator other = (Operator) obj;
		return id == other.id;
	}
}

class RequestService {
	private List<Request> requests = new ArrayList<Request>();
	public void add() {
		Request request = new Request();
		requests.add(request);
		System.out.println("Додавання нової заявки: " + request);
	}
	public void changeStatus(Operator operator) {
		for (Request request : requests) {
			if (request.getStatus() == Request.Status.NEW) {
				request.changeStatus(operator);
				break;
			}
		}
		System.out.println("Початок обробки заявки оператором: " + operator);
	}
	public void closeStatus(Operator operator) {
		for (Request request : requests) {
			if (request.getOperator() == operator && request.getStatus() == Request.Status.INPROGRESS) {
				request.closeStatus();
				break;
			}
		}
		System.out.println("Закриття заявки оператором " + operator);
	}
	public List<Request> requests(Request.Status status) {
		return requests.stream().filter(x -> x.getStatus() == status).collect(Collectors.toList());
	}
	public List<Request> requests(Operator operator) {
		return requests.stream().filter(x -> x.getOperator() == operator && x.getStatus() == Request.Status.INPROGRESS).collect(Collectors.toList());
	}
	public Request check(int id) {
		return requests.stream().filter(x -> x.getId() == id).findAny().orElse(null);
	}
	public void print() {
		System.out.println("Заявки: ");
		if (requests == null || requests.size() == 0) {
			System.out.println("відсутні");
		}
		else {
			for (Request request : requests) {
				System.out.println(request);
			}
		}
	}
}

public class electronicQueue {

	public static void main(String[] args) {
		RequestService desk = new RequestService();
		desk.print();
		System.out.println("\n");
		desk.add();
		desk.add();
		System.out.println("\n");
		desk.print();
		System.out.println("\n");
		Operator h1 = new Operator();
		desk.changeStatus(h1);
		System.out.println("\n");
		desk.print();
		System.out.println("\n");
		Operator h2 = new Operator();
		desk.add();
		System.out.println("\n");
		desk.changeStatus(h1);
		desk.changeStatus(h2);
		System.out.println("\n");
		desk.add();
		System.out.println("\n");
		desk.print();
		System.out.println("\n");
		System.out.println(desk.requests(Request.Status.CLOSED));
		System.out.println(desk.requests(Request.Status.NEW));
		System.out.println(desk.requests(Request.Status.INPROGRESS));
		System.out.println("\n");
		System.out.println(desk.requests(h1));
		System.out.println(desk.requests(h2));
		System.out.println("\n");
		System.out.println(desk.check(1));
		System.out.println(desk.check(2));
		System.out.println(desk.check(999));
		System.out.println("\n");
		desk.closeStatus(h1);
		System.out.println("\n");
		desk.print();
		System.out.println("\n");
		System.out.println(desk.requests(Request.Status.CLOSED));
		System.out.println(desk.requests(Request.Status.NEW));
		System.out.println(desk.requests(Request.Status.INPROGRESS));
	}
}