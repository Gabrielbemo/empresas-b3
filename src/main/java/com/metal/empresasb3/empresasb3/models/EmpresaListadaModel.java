package com.metal.empresasb3.empresasb3.models;

public class EmpresaListadaModel {
    private String atualizadoEm;
    private String escriturador;
    private String nomePregao;
    private String codigoNegociacao;
    private String CNPJ;
    private String atividadePrincipal;
    private String classificacaoSetorial;
    private String site;

    public EmpresaListadaModel() {
    }

    public EmpresaListadaModel(String atualizadoEm, String escriturador, String nomePregao, String codigoNegociacao, String CNPJ, String atividadePrincipal, String classificacaoSetorial, String site) {
        this.atualizadoEm = atualizadoEm;
        this.escriturador = escriturador;
        this.nomePregao = nomePregao;
        this.codigoNegociacao = codigoNegociacao;
        this.CNPJ = CNPJ;
        this.atividadePrincipal = atividadePrincipal;
        this.classificacaoSetorial = classificacaoSetorial;
        this.site = site;
    }

    public String getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(String atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }

    public String getEscriturador() {
        return escriturador;
    }

    public void setEscriturador(String escriturador) {
        this.escriturador = escriturador;
    }

    public String getNomePregao() {
        return nomePregao;
    }

    public void setNomePregao(String nomePregao) {
        this.nomePregao = nomePregao;
    }

    public String getCodigoNegociacao() {
        return codigoNegociacao;
    }

    public void setCodigoNegociacao(String codigoNegociacao) {
        this.codigoNegociacao = codigoNegociacao;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getAtividadePrincipal() {
        return atividadePrincipal;
    }

    public void setAtividadePrincipal(String atividadePrincipal) {
        this.atividadePrincipal = atividadePrincipal;
    }

    public String getClassificacaoSetorial() {
        return classificacaoSetorial;
    }

    public void setClassificacaoSetorial(String classificacaoSetorial) {
        this.classificacaoSetorial = classificacaoSetorial;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
