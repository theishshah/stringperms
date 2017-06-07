package com.ish;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        //using Scanner to fetch user input
	    Scanner scanner = new Scanner(System.in);

	    //stop condition
        boolean run = true;

        while(run){
            //prompt user for data or exit condition
            System.out.println("Type the string you want permutations for OR 'quit' to exit");
            String input = scanner.nextLine();

            //given the exit condition, the loop will break
            if(input.equalsIgnoreCase("quit")){
                run = false;
                break;
            }

            //execute permutation function
            //require space at end of input due to bug in permutations not interacting with final character
            Set<String> output = permutations(input+" ");

            //print output data
            System.out.println("Permutations of "+input+":");
            for(String str:output){
                System.out.println(str);
            }
        }
    }

    //using Set to avoid repetitions
    //has issue of ignoring final character of input, however repairing it damages runtime and it is more efficient
    //for the main to feed in a blank char at the end of the input
    public static Set<String> permutations(String input){
        Set<String> perms = new HashSet<String>();

        //recursive base case
        if(input.equals("")){
            return perms;
        }

        char first = input.charAt(0);

        //if input is not just 1 char, continue process
        if(input.length()>1){
            //tail string to put through recursive process
            String tail = input.substring(1);

            //subset of permutations to work with
            Set<String> recursive_perms = permutations(tail);

            //iterates through the new set of permutations
            for (String perm:recursive_perms){
                //inserts first char in every potential location of the string
                for(int i = 0; i<perm.length(); i++){
                    perms.add(perm.substring(0,i)+first+perm.substring(i));
                }
            }
        }
        else{
            //if the string is only one character, it is the only permutation
            perms.add(""+first);
        }
        return perms;
    }
}
