package co.junwei.scheme;

import co.junwei.digitalsignature.Dsa;
import co.junwei.digitalsignature.SignatureObject;
import it.unisa.dia.gas.jpbc.Element;
public class bswabeCph {
	
	public Element w; /* G_1 */
	public Element w0; /* G_1 */
	public Element u_gate; /* G_1*/
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
		ans=ans&check(w)&check(w0)&check(u_gate);
		return ans;
	}
	//public BswabePolicy p;
}
