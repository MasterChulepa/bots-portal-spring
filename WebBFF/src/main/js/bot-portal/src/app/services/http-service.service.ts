import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../portal-body/user-info/User";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HttpServiceService {

  constructor(private http: HttpClient) {
  }

  fetchUserData(): Observable<User[]>{
    return this.http.get<User[]>("http://localhost:9000/users")
  }
  deleteUser(id: string){
    return this.http.delete(`http://localhost:9000/users/${id}`)
  }
}
