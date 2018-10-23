package design_patterns.visitor;

public interface IWorker {
  String getName();

  int getAge();

  int getSalary();

  void calculateSalary(Istatistics handler);
}
