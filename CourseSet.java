/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torontodataset;

import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;
import java.io.BufferedReader;
import java.io.FileReader;

public class CourseSet {
	Set<String> course;
	public CourseSet(final String fileDir) {
		try {
			final FileReader fr = new FileReader(fileDir);
			final BufferedReader br = new BufferedReader(fr);

			readCourse(br);
		} catch (final Exception e) {
		}
	}

	public Set<String> getCourse() {
		return this.course;
	}

	public void readCourse(final BufferedReader br) {
		course = new HashSet<String>();
		String courseLine = null;
		try {
			while ((courseLine = br.readLine()) != null) {
				final String[] arr = courseLine.split(" ");
				course.add(arr[0]);
			}
		} catch (final Exception e) {
			System.out.println("Error boss");
		}
	}

	public void printSet() {
		final Set<String> sortedCourse = new TreeSet<String>(course);
        System.out.println(sortedCourse);
	}
	
	public int getSize() {
		return course.size();
	}
	
}
