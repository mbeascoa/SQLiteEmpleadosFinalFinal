package com.example.sqliteempleados;

import java.util.Date;

public class Empleados {
    private int mCodigoemp,mSalario, mComision,mNumeroDepartamento;
    private String mNombre, mApellido, mOficio, mDireccion,mFechaAlta;

    public Empleados() {
    }


    public Empleados(int mCodigoemp, String mNombre, String mApellido, String mOficio, String mDireccion, String mFechaAlta, int mSalario, int mComision, int mNumeroDepartamento) {
        this.mCodigoemp = mCodigoemp;
        this.mSalario = mSalario;
        this.mComision = mComision;
        this.mNumeroDepartamento = mNumeroDepartamento;
        this.mNombre = mNombre;
        this.mApellido = mApellido;
        this.mOficio = mOficio;
        this.mDireccion = mDireccion;
        this.mFechaAlta = mFechaAlta;
    }

    public int getCodigoemp() {
        return this.mCodigoemp;
    }

    public void setCodigoemp(int mCodigoemp) {
        this.mCodigoemp = mCodigoemp;
    }

    public int getSalario() {
        return this.mSalario;
    }

    public void setSalario(int mSalario) {
        this.mSalario = mSalario;
    }

    public int getComision() {
        return this.mComision;
    }

    public void setComision(int mComision) {
        this.mComision = mComision;
    }

    public int getNumeroDepartamento() {
        return mNumeroDepartamento;
    }

    public void setNumeroDepartamento(int mNumeroDepartamento) {
        this.mNumeroDepartamento = mNumeroDepartamento;
    }

    public String getNombre() {
        return this.mNombre;
    }

    public void setmNombre(String mNombre) {
        this.mNombre = mNombre;
    }

    public String getApellido() {
        return this.mApellido;
    }

    public void setApellido(String mApellido) {
        this.mApellido = mApellido;
    }

    public String getOficio() {
        return this.mOficio;
    }

    public void setOficio(String mOficio) {
        this.mOficio = mOficio;
    }

    public String getDireccion() {
        return this.mDireccion;
    }

    public void setDireccion(String mDireccion) {
        this.mDireccion = mDireccion;
    }

    public String getFechaAlta() {
        return this.mFechaAlta;
    }

    public void setFechaAlta(String mFechaAlta) {
        this.mFechaAlta = mFechaAlta;
    }


    @Override
    public String toString() {
        return "Empleados{" +
                "mCodigoemp=" + mCodigoemp +
                ", mSalario=" + mSalario +
                ", mComision=" + mComision +
                ", mNumeroDepartamento=" + mNumeroDepartamento +
                ", mNombre='" + mNombre + '\'' +
                ", mApellido='" + mApellido + '\'' +
                ", mOficio='" + mOficio + '\'' +
                ", mDireccion='" + mDireccion + '\'' +
                ", mFechaAlta=" + mFechaAlta +
                '}';
    }
}