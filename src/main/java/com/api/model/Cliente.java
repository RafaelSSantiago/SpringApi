package com.api.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String Nome;

    @Column(name = "description")
    private String description;

    @Column(name = "published")
    private boolean published;

    public Cliente(String Nome, String description, boolean published) {
        this.Nome = Nome;
        this.description = description;
        this.published = published;
    }

    @Override
    public String toString() {
        return "Tutorial [id=" + id + ", title=" + Nome + ", desc=" + description + ", published=" + published + "]";
    }


}
