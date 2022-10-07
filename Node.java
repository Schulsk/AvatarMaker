


class Node{
    String string;
    int weight;
    public Node(){
        string = null;
        weight = 0;
    }

    public String toString(){
        String string = "";
        if (this.string == null){
            string += "empty";
        }
        else{
            string += this.weight;
        }
        return string;
    }

    public void setString(String string){
        this.string = string;
    }

    public String getValue(){
        return string;
    }

    public void setWeight(int newWeight){
        weight = newWeight;
    }

    public int getWeight(){
        return weight;
    }

    public void increaseWeight(int amount){
        weight += amount;
    }
}
