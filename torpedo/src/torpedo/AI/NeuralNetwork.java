import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class NeuralNetwork {
	Float[] FIRSTLAYERWEIGHTS;
	Float[] SECONDLAYERWEIGHTS;
	Float[] OUTPUTWEIGHTS;
	Float[] FIRSTLAYERBIAS;
	Float[] SECONDLAYERBIAS;
	Float[] OUTPUTBIAS;
	
	
	void setNetwork(int BoardSize) throws IOException
	{
		loadNetwork("weights/bias1.txt");
	}
	
	void loadNetwork(String networkPath) throws IOException
	{		
		loadFirstBias();
		loadSecondBias();
		loadOutputBias();
		loadFirstWeights();
		loadSecondWeights();
		loadOutputWeights();
	}
	
	void loadFirstBias() throws IOException
	{
		Scanner inFile1 = new Scanner(new File("weights/bias1.txt")).useDelimiter("\n");
		List<Float> temps = new ArrayList<Float>();
		while (inFile1.hasNext()) {
		      // find next line
		      float token1 = Float.parseFloat(inFile1.next());
		      temps.add(token1);
		    }
		    inFile1.close();

		    this.FIRSTLAYERBIAS = temps.toArray(new Float[0]);	    
		    
	}
	
	void loadSecondBias() throws IOException
	{
		Scanner inFile1 = new Scanner(new File("weights/bias2.txt")).useDelimiter("\n");
		List<Float> temps = new ArrayList<Float>();
		while (inFile1.hasNext()) {
		      // find next line
		      float token1 = Float.parseFloat(inFile1.next());
		      temps.add(token1);
		    }
		    inFile1.close();

		    this.SECONDLAYERBIAS = temps.toArray(new Float[0]);	    		   
	}	
	
	void loadOutputBias() throws IOException
	{
		Scanner inFile1 = new Scanner(new File("weights/biasout.txt")).useDelimiter("\n");
		List<Float> temps = new ArrayList<Float>();
		while (inFile1.hasNext()) {
		      // find next line
		      float token1 = Float.parseFloat(inFile1.next());
		      temps.add(token1);
		    }
		    inFile1.close();

		    this.OUTPUTBIAS = temps.toArray(new Float[0]);	    		   
	}
	
	void loadFirstWeights() throws IOException
	{
		Scanner inFile1 = new Scanner(new File("weights/weight1.txt")).useDelimiter(" |\n");
		List<Float> temps = new ArrayList<Float>();
		while (inFile1.hasNext()) {
		      // find next line
		      float token1 = Float.parseFloat(inFile1.next());
		      temps.add(token1);
		    }
		    inFile1.close();

		    this.FIRSTLAYERWEIGHTS = temps.toArray(new Float[0]);
	}

	void loadSecondWeights() throws IOException
	{
		Scanner inFile1 = new Scanner(new File("weights/weight2.txt")).useDelimiter(" |\n");
		List<Float> temps = new ArrayList<Float>();
		while (inFile1.hasNext()) {
		      // find next line
		      float token1 = Float.parseFloat(inFile1.next());
		      temps.add(token1);
		    }
		    inFile1.close();

		    this.SECONDLAYERWEIGHTS = temps.toArray(new Float[0]);
	}

	void loadOutputWeights() throws IOException
	{
		Scanner inFile1 = new Scanner(new File("weights/weightout.txt")).useDelimiter(" |\n");
		List<Float> temps = new ArrayList<Float>();
		while (inFile1.hasNext()) {
		      // find next line
		      float token1 = Float.parseFloat(inFile1.next());
		      temps.add(token1);
		    }
		    inFile1.close();

		    this.OUTPUTWEIGHTS = temps.toArray(new Float[0]);
	}
	
	int argMax(Float[] values)
	{
		int index = 0;
		Float maxValue = (float) -100.0;
		for(int i=0;i<(values.length); i++)
		{
			if (values[i] > maxValue)
			{
				maxValue = values[i];
				index = i;
			}
		}
		return index;
	}
	
	float tanh(Float value)
	{
		return (float) java.lang.Math.tanh(value);
	}
	
	Float[] layerOutput(Float[] layerInputs, Float[] layerWeights, Float[] layerBias, boolean tanH, int layerSize)
	{
		List<Float> temps = new ArrayList<Float>(layerSize);	
		float token1 = 0;
		int weightIndex = 0;
		for(int i = 0; i<layerSize; i++) 
		{
			for (int j = 0; j<layerInputs.length; j++) 
			{
			      weightIndex = i + j*layerSize;
			      token1 += layerInputs[j] * layerWeights[weightIndex];
			      
			}   	
			float value = token1 + layerBias[i];
			if(tanH)
			{
			   value = tanh(value);
			}
			temps.add(value);
			token1 = 0;		    	  
			
		      
		    
		}
		
		Float[] firstLayerOutput = temps.toArray(new Float[0]);
		return firstLayerOutput;
	}
	
	Float[] output(Float[] input)
	{
		Float[] firstLayerOutput = layerOutput(input, this.FIRSTLAYERWEIGHTS, this.FIRSTLAYERBIAS, true, 128);
		Float[] secondLayerOutput = layerOutput(firstLayerOutput, this.SECONDLAYERWEIGHTS, this.SECONDLAYERBIAS, true, 128);
		Float[] output = layerOutput(secondLayerOutput, this.OUTPUTWEIGHTS, this.OUTPUTBIAS, false, 100);
		for (int i = 0; i<input.length; i++)
		{
			if(input[i]==-1 || input[i]==1)
			{
				output[i] = (float) -99;
			}
		}
		return output;
	}
	
	public int nextStep(Float[] board)
	{
		return argMax(output(board));
	}
	
}
