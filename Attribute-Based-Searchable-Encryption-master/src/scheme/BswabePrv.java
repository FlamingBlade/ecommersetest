package scheme;

import digitalsignature.Dsa;
import digitalsignature.SignatureObject;

//import java.util.ArrayList;

import it.unisa.dia.gas.jpbc.Element;

public class BswabePrv {
	/*
	 * A private key
	 */
	public Element v;  /* G_2 */
	public Element sig_user; /* G_2 */
	public Element y_user; /* G_T */
	
	public Element sig[];
	public Element y[];
	boolean check(Element e) throws Exception
	{
		String inputString=e.toString();
		SignatureObject temp;
		inputString=e.toString();
		temp=Dsa.GenerateSignature(inputString);
      	boolean test=Dsa.VerifySignature(temp,inputString);
		return test;
	}
	public boolean checkSignature()throws Exception
	{
		boolean ans=true;
		ans=ans&check(v)&check(sig_user)&check(y_user);
		for(Element e:sig)
		ans=ans&check(e);
		for(Element e:y)
		ans=ans&check(e);
		return ans;
	}
	
	
	//ArrayList<BswabePrvComp> comps; /* BswabePrvComp */
}