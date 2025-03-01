class Person extends Object {
        public int id;
        public String name;
        private long born;

        public Person(int id, String name, long born) {
                this.id = id;
                this.name = name;
                this.born = born;
        }

        public int getId() {
                return id;
        }

        public String getName() {
                return name;
        }

        public long getBorn() {
                return born;
        }
}

