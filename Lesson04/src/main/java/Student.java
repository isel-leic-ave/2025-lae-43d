public class Student extends Person {
        public int number;

        public Student(int id, String name, long born, int number) {
                super(id, name, born);
                this.number = number;
        }

        public int getNumber() {
                return number;
        }
}
