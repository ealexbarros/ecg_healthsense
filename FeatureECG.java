package transform_artigo2;

public class FeatureECG {

	public FeatureECG(double mediaQ,double mediaR, double mediaS,double desvioQ2, double desvioR2, double desvioS2,  double durationR, double timeRR, double rqAmp) {
		this.meanQ =mediaQ;
		this.meanR =mediaR;
		this.meanS=mediaS;
		this.desvioQ= desvioQ2;
		this.desvioR= desvioR2;
		this.desvioS=desvioS2;
		this.durationR=durationR;
		this.timeRR=timeRR;
		this.rqAmp=rqAmp;
	}
	double meanQ;
	double meanR;
	double meanS;
	double desvioQ;
	double desvioR;
	double desvioS;
	
	/*New Features added*/
	 double durationR;
	 double timeRR;
	 double rqAmp;
	 
	
	
}
