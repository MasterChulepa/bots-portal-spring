import {Component, EventEmitter, Input, Output} from '@angular/core';
import {User} from "./User";

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent {
  @Input() user!: User
  @Output() deleteUserEvent: EventEmitter<string> = new EventEmitter<string>();

  deleteUser() {
    this.deleteUserEvent.emit(this.user.id);
  }
}
