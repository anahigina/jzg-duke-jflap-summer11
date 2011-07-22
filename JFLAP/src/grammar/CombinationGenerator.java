package grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import JFLAPnew.formaldef.symbols.variable.Variable;

/**
 * Provides a series of methods for generating combinations of things.
 * Uses simple recursion, but is very algorithmically inefficient. A simple 
 * improvement would be the use of memoization, but then the methos could not
 * be static.
 * @author Julian Genkins
 *
 */
public class CombinationGenerator {
	

	public static <T> List<Set<T>> generateCombinationsOfSize(Set<T> input, int n){
		
		if (n == 1) return CombinationGenerator.splitToSets(input);
		
		List<Set<T>> results = new ArrayList<Set<T>>();
		Set<T> clone = new HashSet<T>(input);
		Iterator<T> iter = input.iterator();
		while (iter.hasNext()){
			T obj = iter.next();
			clone.remove(obj);
			for (Set<T> set: generateCombinationsOfSize(clone, n-1)){
				set.add(obj);
				results.add(set);
			}
				
		}
		
		return results;
	}

	public static <T> List<Set<T>> generateAllCombinations(Set<T> input){
		List<Set<T>> output = new ArrayList<Set<T>>();
		for (int n = 1; n < input.size()+1; n++){
			output.addAll(generateCombinationsOfSize(input, n));
		}
		return output;
	}
	
	private static <T> List<Set<T>> splitToSets(Set<T> input){
		List<Set<T>> results = new ArrayList<Set<T>>();
		for(T obj : input){
			Set<T> temp = new HashSet<T>();
			temp.add(obj);
			results.add(temp);
		}
		return results;
	}
	
	
	public static void main (String[] args){
		Set<Integer> test = new HashSet<Integer>(Arrays.asList(new Integer[]{1,2,3}));
		
		System.out.println(CombinationGenerator.generateAllCombinations(test));
	}
	
}
