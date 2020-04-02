package com.gsantos;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVPrinter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.gsantos.Veiculo;
import com.gsantos.Placa;
import java.lang.Object;
/*
Exemplo simples de uso da API Apache Commons CVS
Extrair o arquivo commons-csv-1.7.jar para o diretorio do projeto
Para compilar no Windows: javac -cp .;.\commons-csv-1.7.jar App.java
Para compilar no Linux: javac -cp commons-csv-1.7.jar App.java
Para executar no windows: java -cp .;.\commons-csv-1.7.jar App
Para executar no Linux: java -cp .:commons-csv-1.7.jar App
Para executar: java -cp .;.\commons-csv-1.7.jar App.java
*/
public class App {
    private static final String SAMPLE_CSV_FILE_PATH = "..\\resources\\veiculos.dat";
    private static ArrayList<Veiculo> veiculos;

    public static void main(String[] args) throws IOException {
        veiculos = new ArrayList<Veiculo>();
        carregaVeiculos();
        veiculos.forEach(x->System.out.println(x));

        Placa p = new Placa("BRASIL", "GUI2W11");

        veiculos.add(new Veiculo(p, "NISSAN", "PRETO", "SUV"));

        salvaVeiculos();
    }

    public static void carregaVeiculos(){
        try {
            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase());
            for (CSVRecord csvRecord : csvParser) {
                // Accessing Values by Column Index
                String placa = csvRecord.get(0);
                String marca = csvRecord.get(1);
                String cor = csvRecord.get(2);
                String categoria = csvRecord.get(3);

                Placa p = new Placa("BRASIL", placa);

                veiculos.add(new Veiculo(p, marca, cor, categoria));

                System.out.println("Record No - " + csvRecord.getRecordNumber());
                System.out.println("---------------");
                System.out.println("Placa : " + placa);
                System.out.println("Marca : " + marca);
                System.out.println("Cor : " + cor);
                System.out.println("Categoria : " + categoria);
                System.out.println("---------------\n\n");
            }
            reader.close();
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void salvaVeiculos(){
        try {
            Writer writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE_PATH));

            CSVPrinter printer = CSVFormat.DEFAULT.withHeader("placa","marca","cor","categoria").print(writer);
            

            for (Veiculo v : veiculos) {
                printer.printRecord(v.getPlaca(), v.getMarca(), v.getCor(), v.getCategoria());
            }

            writer.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    
    }
}
