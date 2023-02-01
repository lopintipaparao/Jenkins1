package com.nopcommerce.utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ReadData {
    private FileInputStream instream;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ReadData(String filename) throws Exception {
        String filepath=".\\src\\main\\resources\\testdata\\"+filename+".xlsx";
        instream = new FileInputStream(filepath);
        workbook = new XSSFWorkbook(instream);
        sheet=workbook.getSheet("Sheet1");
    }

    public String getData(int row ,int cell)
    {
        return sheet.getRow(row).getCell(cell).getStringCellValue();
    }

    public List<String> getData(int row )
    {
        List<String> rowData=new ArrayList<String>();
        int cellsCount=sheet.getRow(row).getLastCellNum(); //count
        for(int i=0;i<cellsCount;i++)
        {
           String data= sheet.getRow(row).getCell(i).getStringCellValue();
           rowData.add(data);
        }
        return rowData;
    }

    public List<String> getData(String colname)
    {
        List<String> colData = new ArrayList<String>();
              List<String> rowData=getData(0);
              int colNo=0;
             for(int i=0;i<rowData.size();i++)
              {
                  if(rowData.get(i).equals(colname)) {
                      colNo = i;
                      break;
                  }
              }
            int lastRowNo=sheet.getLastRowNum();
             for(int i=1;i<=lastRowNo;i++)
             {
                 String data= sheet.getRow(i).getCell(colNo).getStringCellValue();
                 colData.add(data);
             }

             return colData;
    }

    public String [][] getData()
    {
        int lastrowNo=sheet.getLastRowNum();
        int cellsCount=sheet.getRow(0).getLastCellNum();
        String info[][] = new String[lastrowNo][cellsCount];
        int k=0,l;
        for(int i=1;i<=lastrowNo;i++)
        {
            l=0;
           for(int j=0;j<cellsCount;j++)

           {
               String data=sheet.getRow(i).getCell(j).getStringCellValue();
               info[k][l]=data;
               l++;
           }
           k++;
        }

return info;
    }


    public static void main(String[] args) throws Exception {
        ReadData readData = new ReadData("loginpage");
        String data=readData.getData(1,1);
        System.out.println(data);
        List<String> rowData=readData.getData(1);
        System.out.println(rowData);

       List<String> colData= readData.getData("email");
       System.out.println("Col Data : "+colData);

      String info[][]= readData.getData();

      for (int i=0;i<info.length;i++)
      {
          for (int j=0;j<info[i].length;j++)
          {
              System.out.print(info[i][j]+" ");
          }
          System.out.println();
      }
    }
}
