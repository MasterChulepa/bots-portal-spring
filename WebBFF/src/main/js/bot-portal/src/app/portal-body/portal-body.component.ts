import {Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "./user-info/User";
import {HttpServiceService} from "../services/http-service.service";

@Component({
  selector: 'app-portal-body',
  templateUrl: './portal-body.component.html',
  styleUrls: ['./portal-body.component.css']
})
export class PortalBodyComponent {
  users: User[] = [];
  filteredUsers: User[] = []

  constructor(private http: HttpClient, private httpService: HttpServiceService) {
  }

  ngOnInit() {
    this.update();
  }

  update() {
    this.httpService.fetchUserData().subscribe(response => {
      this.users = response;
      this.filteredUsers = this.users.slice();
    });
  }


  onFilterUsers(id: string) {
    this.filteredUsers = this.users.filter(user => user.id.toString().includes(id));
  }

  onDeleteUser(id: string) {
    this.httpService.deleteUser(id).subscribe(() => {
      this.filteredUsers = this.users.filter(user => user.id !== id).slice();
    });
  }
}
