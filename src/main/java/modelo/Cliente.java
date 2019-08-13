package modelo;

import repository.ClienteRepository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 60, nullable = false)
    private String nome;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private CNH cnh;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Endereco endereco;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Reserva> reservas = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public CNH getCnh() {
        return cnh;
    }

    public void setCnh(CNH cnh) {
        this.cnh = cnh;
    }

    public boolean reservaEmAberto() {
        for (Reserva r : this.reservas) {
            if (r.getStatusReserva().equals(StatusReserva.ABERTA)) {
                return true;
            }
        }
        return false;
    }

  public boolean checaValidadeDaCNH(){
        if(cnh.cnhDentroDaValidade()){
            return true;
        } else {
            return false;
        }
  }

    @Override
    public String toString() {
        return "\n " +
                "Id = " + id + "\n " +
                "Nome = '" + nome + '\'' + "\n " +
                "CNH = " + cnh.getNumeroDaCNH() + " / Categoria: " + cnh.getCategoria() + "\n " +
                "Endereco =  Bairro: " + endereco.getBairro() + "Logadouro:  " + endereco.getRua() + "Numero:" + endereco.getNumero() +
                "Reservas =  " + reservas +
                '\n';
    }
}




