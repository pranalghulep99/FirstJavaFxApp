package com.internshala.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InStream_Demo {
	public static void main(String[] args) {
//		1 Create an Array to Range Value from 0, 1, ...100
//		Traditional logic

//		int [] num=new int[101];
//		for (int i=0; i<101;i++){
//			num[i]=i;
//		}
//		System.out.println(Arrays.toString(num));


		int[] numberArray= IntStream.rangeClosed(0, 100).toArray();
		System.out.println(Arrays.toString(numberArray));

//      Create an ArrayList<Integer> in Range of 0 to 100;

		List<Integer> arrayList=IntStream.rangeClosed(0, 100)
				.mapToObj(i -> new Integer(i))
				.collect(Collectors.toList());
		System.out.println(arrayList);
	}
}
