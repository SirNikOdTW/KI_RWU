import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        var root = new Node(0);

        var childOne = new Node(1);
        var childTwo = new Node(2);
        var childThree = new Node(3);

        var childrenLayer1 = List.of(childOne, childTwo, childThree);
        root.addChildren(childrenLayer1);

        var childFour = new Node(4);
        var childFive = new Node(5);

        var children2 = List.of(childFour, childFive);
        childOne.addChildren(children2);

        var childSix = new Node(6);
        var children3 = List.of(childSix);
        childTwo.addChildren(children3);

        System.out.println(root);


        System.out.println(new BreadthFirstSearch().breadthFirstSearch(List.of(root), new Node(2)));
    }
}
