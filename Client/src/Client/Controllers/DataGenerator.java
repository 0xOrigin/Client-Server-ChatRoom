package Client.Controllers;

import java.util.Random;

public class DataGenerator {

    DataGenerator(){

    }

    String generateID(String name){
        StringBuilder generatedID = new StringBuilder();
        name = name.replaceAll(" ", "_");
        name = name.length() > 5 ? name.substring(0, 5) : name;
        generatedID.append(name);
        int numOfRandoms = 5;
        generatedID.append(this.generateRandomNumbers(numOfRandoms));
        return generatedID.toString();
    }

    private String generateRandomNumbers(int count){
        StringBuilder randomNumbers = new StringBuilder();

        for(int i = 0; i < count; i++)
            randomNumbers.append(generateRandomNumber());

        return randomNumbers.toString();
    }

    private int generateRandomNumber(){
        return new Random().nextInt(9);
    }
}
