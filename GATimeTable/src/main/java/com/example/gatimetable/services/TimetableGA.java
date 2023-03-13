package com.example.gatimetable.services;


import com.example.gatimetable.repository.empl.LoadData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;

@Component
public class TimetableGA {
	LoadData loadData;
    @Autowired
	public TimetableGA(LoadData loadData){
		this.loadData = loadData;
	}
    public List<TimetableResponse> TmGA() {
    	// Get a Timetable object with all the available information.
        Timetable timetable = loadData.initializeTimetable();
        
        // Initialize GA
        GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.01, 0.9, 2, 5);
        
        // Initialize population
        Population population = ga.initPopulation(timetable);
        
        // Evaluate population
        ga.evalPopulation(population, timetable);
        
        // Keep track of current generation
        int generation = 1;
        StringBuilder output_str =new StringBuilder();
        // Start evolution loop
        while (ga.isTerminationConditionMet(generation, 1000) == false
            && ga.isTerminationConditionMet(population) == false) {
            // Print fitness
            output_str.append("G" + generation + " Best fitness: " + population.getFittest(0).getFitness()).append("\n");
            // Apply crossover
            population = ga.crossoverPopulation(population);

            // Apply mutation
            population = ga.mutatePopulation(population, timetable);

            // Evaluate population
            ga.evalPopulation(population, timetable);

            // Increment the current generation
            generation++;
        }

        // Print fitness
        timetable.createClasses(population.getFittest(0));
		output_str.append("\n Solution found in " + generation + " generations")
				.append("\n Final solution fitness: " + population.getFittest(0).getFitness())
				.append("\n Clashes: " + timetable.calcClashes());
        // Print classes
        List<TimetableResponse> timetableResponses = new ArrayList<>();
        Class classes[] = timetable.getClasses();
        int classIndex = 1;
        for (Class bestClass : classes) {
            TimetableResponse timetableResponse = new TimetableResponse();
            timetableResponse.setModule(timetable.getModule(bestClass.getModuleId()).getModuleName());
            timetableResponse.setGroup(timetable.getGroup(bestClass.getGroupId()).getGroupId());
            timetableResponse.setRoom(timetable.getRoom(bestClass.getRoomId()).getRoomNumber());
            timetableResponse.setProfessor(timetable.getProfessor(bestClass.getProfessorId()).getProfessorName());
            timetableResponse.setTime(timetable.getTimeslot(bestClass.getTimeslotId()).getTimeslot());
//			output_str.append("\n \n Class " + classIndex + ":")
//					.append("\nModule: "  + timetable.getModule(bestClass.getModuleId()).getModuleName())
//					.append("\nGroup: " + timetable.getGroup(bestClass.getGroupId()).getGroupId())
//					.append("\n Room: " + timetable.getRoom(bestClass.getRoomId()).getRoomNumber())
//					.append("\n Professor: " + timetable.getProfessor(bestClass.getProfessorId()).getProfessorName())
//					.append("\n Time: " + timetable.getTimeslot(bestClass.getTimeslotId()).getTimeslot());
            timetableResponses.add(timetableResponse);
            classIndex++;
        }
		return timetableResponses;
    }

}
