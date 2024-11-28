package com.example.projeto_bd_2023;

public class ModeloDados {
    int idAgendamento;
    String email, data, hora;

    //método construtor
    public ModeloDados() {

    }
    //método construtor
    public ModeloDados(int _id, String _email, String _data, String _hora) {
        this.setIdAgendamento(_id);
        this.setEmail(_email);
        this.setData(_data);
        this.setHora(_hora);
    }
    //set
    public void setIdAgendamento(int _id){
        this.idAgendamento = _id;
    }
    public void setEmail(String _email){
        this.email = _email;
    }
    public void setHora(String _hora){
        this.hora = _hora;
    }
    public void setData(String _data){
        this.data = _data;
    }

    //get
    public int getIdAgendamento() {
        return this.idAgendamento;
    }
    public String getEmail() {
        return this.email;
    }
    public String getData() {
        return this.data;
    }
    public String getHora(){
        return this.hora;
    }
}
