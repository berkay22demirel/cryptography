import javax.swing.JOptionPane;
public class SuperArtanDizi {
	public static void main(String[] args) {
		String DiziBoyutuString = JOptionPane.showInputDialog("Süper Artan Dizinin Boyutunu Girin...");
		int DiziBoyutu = Integer.parseInt(DiziBoyutuString);
		int SuperArtDizi[] = new int [DiziBoyutu];
		int i;
		for(i=0;i<DiziBoyutu;i++){
			SuperArtDizi[i] = (int)Math.pow(2, i);
		}
		int SonucDizi[] = new int[DiziBoyutu];
		String BulunacakSayiString = JOptionPane.showInputDialog("Bulmak Ýstediðiniz Sayýyý Girin...");
		int BulunacakSayi = Integer.parseInt(BulunacakSayiString);
		for(i=SuperArtDizi.length-1;i>=0;i--){
			if(SuperArtDizi[i] <= BulunacakSayi){
				SonucDizi[i] = 1;
				BulunacakSayi = BulunacakSayi - SuperArtDizi[i];
			}
			else{
				SonucDizi[i] = 0;
			}
		}
		String sonuc ="";
		for(i=0;i<SuperArtDizi.length;i++){
			sonuc = sonuc + Integer.toString((int)Math.pow(2, i)) + " = " + Integer.toString(SonucDizi[i]) + "   ";
		}
		JOptionPane.showMessageDialog(null, sonuc, "Sonuç",JOptionPane.INFORMATION_MESSAGE);
	}

}
