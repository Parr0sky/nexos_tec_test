import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private httpClient: HttpClient) { }

  // tslint:disable-next-line:typedef
  public getAllMercs(){
    return this.httpClient.get('/mercs/all');
  }

  // @ts-ignore
  // tslint:disable-next-line:typedef
  public addMerc(nombre: any, cantidad: any, fecha: string, usuarioCreacion: any){
    const headers = new HttpHeaders().set('Content-Type', 'text/plain; charset=utf-8');
    return this.httpClient.post(`/mercs/add?nombre=${nombre}&cantidad=${cantidad}&fecha=${fecha}&usuario_creacion=${usuarioCreacion}`, '');
  }

  // @ts-ignore
  // tslint:disable-next-line:typedef
  public editMerc(id: any, nombre: any, cantidad: any, fecha: string, usuarioCreacion: any){
    const headers = new HttpHeaders().set('Content-Type', 'text/plain; charset=utf-8');
    return this.httpClient.put(`/mercs/edit/${id}?nombre=${nombre}&cantidad=${cantidad}&fecha=${fecha}&usuario_mod=${usuarioCreacion}`, '');
  }


  // tslint:disable-next-line:typedef
  public getAllUsers(){
    return this.httpClient.get('/user/all');
  }
}
