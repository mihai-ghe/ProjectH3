

public class Patient implements Entity{

    int id, age; double weight, height;
    String meds, supp, dians;
    int gen;

    Patient(int id, double weight, double height, int gen) {

        this.id = id;
        this.weight = weight;
        this.height = height;
        this.gen = gen;

    }

    Patient() {

    }

}
