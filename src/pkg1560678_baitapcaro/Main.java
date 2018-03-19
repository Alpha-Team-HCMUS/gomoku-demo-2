/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1560678_baitapcaro;

/**
 *
 * @author nhvjm
 */
import java.util.Scanner;
public class Main {
    
    // Khởi tạo bàn cờ.
    public static void TaoBanCo(char[][] a)
    {
        for (int i = 0; i < a.length; i++)
        {
            for (int j = 0; j < a[i].length; j++)
            {
                a[i][j] = '.';
            }
        }
    }
    
    // In bàn cờ.
    public static void InBanCo(char[][] a)
    {
        int dong = 0;
        int cot = 0;
        for (int i = 0; i < a.length; i++) 
        {
            if (i == 0)
            {
                for (int c = 0; c < a[i].length; c++)
                {
                    if (c == 0)
                        System.out.print("     " + cot);
                    else
                        System.out.print("    " + cot);
                    cot++;
                }
                System.out.println();
            }
            System.out.print(dong + "    ");
            for (int j = 0; j < a[i].length; j++) 
            {
                System.out.print(a[i][j] + "    ");
            }
            System.out.println();
            dong++;
        }
    }
    
    public static int NuocDi(int dong, int cot, char[][] a, char quanCo)
    {
        if (a[dong][cot] == '.' && (dong < a.length && cot < a[dong].length))
        {
            a[dong][cot] = quanCo;
            return 1;   // Nước cờ đi thành công.
        }
        return 0;   // Nước cờ đã đi rồi.
    }
    
    // Hàm kiểm tra thắng thua.
    public static int KiemTraDong(char[][] a, int dong, char quanCo)
    {
        int dem = 0;
        for (int i = 0; i < a[dong].length; i++)
        {
            if (a[dong][i] == quanCo)
            {
                dem++;
                if (dem == 5)
                    return 1;
            }
            else
                dem = 0;
        }
        return 0;
    }
    
    public static int KiemTraCot(char[][] a, int cot, char quanCo)
    {
        int dem = 0;
        for (int i = 0; i < a.length; i++)
        {
            if (a[i][cot] == quanCo)
            {
                dem++;
                if (dem == 5)
                    return 1;
            }
            else
                dem = 0;
        }
        return 0;
    }
    
    public static int KiemTraDuongCheo1(char[][] a, int dong, int cot, char quanCo)
    {
        int dem = 0;
        if (dong >= cot)
        {
            dong = dong - cot;
            cot = cot - cot;
        }
        else
        {
            cot = cot - dong;
            dong = dong - dong;
        }
        for (int i = dong, j = cot; i < a.length && j < a[0].length; i++, j++)
        {
            if (a[i][j] == quanCo)
            {
                dem++;
                if (dem == 5)
                    return 1;
            }
            else
                dem = 0;
        }
        return 0;
    }
    
    public static int KiemTraDuongCheo2(char[][] a, int dong, int cot, char quanCo)
    {
        int dem = 0;
        while (dong > 0 || cot < a[0].length-1)
        {
            dong--;
            cot++;
        }
        
        for (int i = dong, j = cot; i < a.length && j >= 0; i++, j--)
        {
            if (a[i][j] == quanCo)
            {
                dem++;
                if (dem == 5)
                    return 1;
            }
            else
                dem = 0;
        }
        return 0;
        
    }
    
    public static int KiemTraThangCuoc(char[][] a, int dong, int cot, char quanCo)
    {
        if (KiemTraDong(a, dong, quanCo) == 1)
            return 1;
        if (KiemTraCot(a, cot, quanCo) == 1)
            return 1;
        if (KiemTraDuongCheo1(a, dong, cot, quanCo) == 1)
            return 1;
        if (KiemTraDuongCheo2(a, dong, cot, quanCo) == 1)
            return 1;
        return 0;
    }
    
    public static void BatDau(char[][] a)
    {
        Scanner in = new Scanner(System.in);
        String buocDi;
        String[] dongCot;
        int dong;
        int cot;
        int kq;
        int kt = 0;
        try
        {
            do
            {
                // Lượt người chơi X.
                if (kt == 0)
                {
                    do
                    {
                    System.out.print("Người chơi X đi (số dòng, số cột):");
                    buocDi = in.nextLine();
                        dongCot = buocDi.split("[ ]");
                        dong = Integer.parseInt(dongCot[0]);
                        cot = Integer.parseInt(dongCot[1]);
                        kq = NuocDi(dong, cot, a, 'X');
                        
                        if (kq == 0)
                        {
                            System.out.println("Vị trí không hợp lệ, mời chơi lại");
                        }
                        else
                        {
                            InBanCo(a);
                            kt = KiemTraThangCuoc(a, dong, cot, 'X');
                            if (kt == 1)
                            {
                                System.out.println("X thắng!!!");
                                break;
                            }
                        }
                    }while(kq == 0 && kt == 0);
                }
                
                // Lượt người chơi O.
                if (kt == 0)
                {
                    do
                    {
                    System.out.print("Người chơi O đi (số dòng, số cột):");
                    buocDi = in.nextLine();
                        dongCot = buocDi.split("[ ]");
                        dong = Integer.parseInt(dongCot[0]);
                        cot = Integer.parseInt(dongCot[1]);
                        kq = NuocDi(dong, cot, a, 'O');
                        
                        if (kq == 0)
                        {
                            System.out.println("Vị trí không hợp lệ, mời chơi lại");
                        }
                        else
                        {
                            InBanCo(a);
                            kt = KiemTraThangCuoc(a, dong, cot, 'O');
                            if (kt == 1)
                            {
                                System.out.println("O thắng!!!");
                                break;
                            }
                        }
                    }while(kq == 0 && kt == 0);
                }
            } while(kt == 0);  // Trong khi chưa có ai thắng cuộc.
            
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        int soDong;
        int soCot;
        char[][] a = null;
        try
        {
            do
            {
                System.out.print("Nhập số dòng bàn cờ >= 9: ");
                soDong = in.nextInt();
                System.out.print("Nhập số cột bàn cờ >= 9: ");
                soCot = in.nextInt();
            } while (soDong < 9 || soCot < 9);
            
            a = new char[soDong][soCot];
            TaoBanCo(a);
            InBanCo(a);
            BatDau(a);
        }
        catch(Exception e)
        {
            System.out.println("Loi");
        }
    }
}
