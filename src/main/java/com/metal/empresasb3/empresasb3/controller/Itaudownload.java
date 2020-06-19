package com.metal.empresasb3.empresasb3.controller;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController()
public class Itaudownload {

    private HashMap<String, String> tdCollections = new HashMap<String, String>();
    private ArrayList<String> twoTrKeys = new ArrayList<>();
    private ArrayList<String> threeTrKeys = new ArrayList<>();

    @RequestMapping("/download")
    public String download(){
        try {
            getHtmlPage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    private HtmlPage getHtmlPage() throws IOException {
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        String searchUrl = "http://bvmf.bmfbovespa.com.br/pt-br/mercados/acoes/empresas/ExecutaAcaoConsultaInfoEmp.asp?CodCVM=19348&ViewDoc=1&AnoDoc=2020&VersaoDoc=1&NumSeqDoc=90684";
        HtmlPage page = client.getPage(searchUrl);
        List<HtmlElement> thElements = page.getByXPath("//div");
        final HtmlElement div = page.querySelector("div#accordionDados");

        addReservedTrKey("Ativo Imobilizado, Investimentos e Intangível");
        addReservedTrKey("Ativo Total");
        addReservedTrKey("Património Líquido");
        addReservedTrKey("Patrimônio Líquido Atribuído à Controladora");

        addReservedTrKey("Receita de Venda");
        addReservedTrKey("Resultado Bruto");
        addReservedTrKey("Resultado de Equivalência Patrimonial");
        addReservedTrKey("Resultado Financeiro");
        addReservedTrKey("Resultado Líquido das Operações Continuadas");
        addReservedTrKey("Lucro (Prejuízo) do Período");
        addReservedTrKey("Lucro (Prejuízo) do Período Atribuído à Controladora");


        addReservedTrKey("Atividades Operacionais");
        addReservedTrKey("Atividades de Investimento");
        addReservedTrKey("Atividades de Financiamento");
        addReservedTrKey("Variação Cambial sobre Caixa e Equivalentes");
        addReservedTrKey("Aumento (Redução) de Caixa e Equivalentes");

        addReservedTrKey("Total de Ações");
        addReservedTrKey("Quantidade de Ações Ordinárias");
        addReservedTrKey("Investidores Institucionais");
        addReservedTrKey("Pessoas Jurídicas");
        addReservedTrKey("Pessoas Físicas");

        addReservedThreeTrKey("Iupar - Itaú Unibanco Participações S.A.");
        addReservedThreeTrKey("Blackrock.inc");
        addReservedThreeTrKey("Itaúsa - Investimentos Itaú S.A.");
        addReservedThreeTrKey("Outros");
        addReservedThreeTrKey("Ações Tesouraria");
        addReservedThreeTrKey("Total");


        List<HtmlElement> trs = div.getByXPath("//tr");
        trs.stream().forEach(f -> {
            List<HtmlElement> tds = f.getByXPath("//td");
            for(int idx = 0;idx < tds.stream().count(); idx++){
                if(twoTrKeys.contains(tds.get(idx).asText())){
                    tdCollections.put(tds.get(idx).asText(),tds.get(idx+1).asText()+"|"+tds.get(idx+2).asText());
                    idx++;
                    idx++;
                }else if(threeTrKeys.contains(tds.get(idx).asText()) && idx != 110){
                    tdCollections.put(tds.get(idx).asText(),tds.get(idx+1).asText()+"|"+tds.get(idx+2).asText()+"|"+tds.get(idx+3).asText());
                    idx++;
                    idx++;
                    idx++;
                }else{
                    tdCollections.put(tds.get(idx).asText(),tds.get(idx+1).asText());
                    idx++;
                }
            }
        });



        tdCollections.put("Códigos de Negociação:",extractNegociationCode(tdCollections.get("Códigos de Negociação:")));

        List<HtmlElement> table = div.getPage(). getByXPath("//table");
        return page;
    }

    private void addReservedTrKey(String key){
        twoTrKeys.add(key);
    }

    private void addReservedThreeTrKey(String key){
        threeTrKeys.add(key);
    }

    private String extractNegociationCode(String codes){
        StringBuilder codigos = new StringBuilder();
        int indexThree = codes.indexOf("3");
        int indexFour = codes.indexOf("4");
        int indexEleven = codes.indexOf("11");
        if(indexThree > 0){
            codigos.append(codes.substring(indexThree-4,indexThree+1)+ ",");
        }
        if(indexFour > 0){
            codigos.append(codes.substring(indexFour-4,indexFour+1)+ ",");
        }
        if(indexEleven > 0){
            codigos.append(codes.substring(indexEleven-4,indexEleven+1)+ ",");
        }
        if(codigos.length() > 0){
            codigos.replace(codigos.length()-1,codigos.length(),"");
        }
        return codigos.toString();
    }

}
