import java.math.BigInteger;


public class MerkleHellman {
	//b sayýsýnýn mod a'ya göre tersini bulan fonksiyon
	public static BigInteger oklit(BigInteger a,BigInteger b){
		BigInteger x1 = BigInteger.valueOf(1);
		BigInteger x2 = BigInteger.valueOf(0);
		BigInteger x3 = a;
		BigInteger y1 = BigInteger.valueOf(0);
		BigInteger y2 = BigInteger.valueOf(1);
		BigInteger y3 = b;
		BigInteger q = BigInteger.valueOf(0);
		int i = 1;
		BigInteger t1 = BigInteger.valueOf(0);
		BigInteger t2 = BigInteger.valueOf(0);
		BigInteger t3 = BigInteger.valueOf(0);
		do{
			if(i == 1){
				q = x3.divide(y3);
				t1 = x1.subtract((q.multiply(y1)));
				t2 = x2.subtract((q.multiply(y2)));
				t3 = x3.subtract((q.multiply(y3)));
			}
			else{
				x1 = y1; x2 = y2; x3 = y3;
                y1 = t1; y2 = t2; y3 = t3;
                q = x3.divide(y3);
                t1 = x1.subtract((q.multiply(y1)));
				t2 = x2.subtract((q.multiply(y2)));
				t3 = x3.subtract((q.multiply(y3)));
			}
			i++;
			if (y3.intValue() == 0)
            {
                break;
            }
		}while (y3.intValue() != 1);
		if (y3.intValue() == 0)
        {
            System.out.println("Sayinin tersi yoktur!!!!");
            return y2;
        }
        else
        {
        	return y2;
        }
	}
	public static void main(String[] args) {
		int SuperArtanDizi[] = {2, 7, 11, 21, 42, 89, 180, 354};
		int W = 0,i;
		for(i = 0;i<SuperArtanDizi.length;i++){
			W = W + SuperArtanDizi[i];
		}
		int q = 881;
		int r = 588;
		int c = 0;
		int BDizisi[] = new int[SuperArtanDizi.length];
		for(i = 0; i<SuperArtanDizi.length; i++){
			BDizisi[i] = (SuperArtanDizi[i] * r)%q;
		}
		String SifrelenecekMetin = "a";
		int m = SifrelenecekMetin.charAt(0);
		System.out.println(m);
		String mBinary = Integer.toBinaryString(m);
		System.out.println(mBinary);
		for(i=0;i<8-mBinary.length();i++){
			mBinary = "0" + mBinary;
		}
		System.out.println(mBinary);
		for(i=0;i<BDizisi.length;i++){
			if(mBinary.charAt(i) == '1'){
				c = c + BDizisi[i];
			}
		}
		System.out.println(c);
		int rters = (oklit(BigInteger.valueOf(q),BigInteger.valueOf(r))).intValue();
		if(rters < 0){
			rters = rters + q;
		}
		System.out.println(rters);
		m = (c * rters) % q;
		String Sonuc = "";
		for(i=SuperArtanDizi.length-1;i>=0;i--){
			if(SuperArtanDizi[i] <= m){
				Sonuc = "1" + Sonuc;
				m = m - SuperArtanDizi[i];
			}
			else{
				Sonuc = "0" + Sonuc;
			}
		}
		int sonuc = Integer.parseInt(Sonuc, 2);
		System.out.println(sonuc);
		System.out.println((char)sonuc);
	}

}
