import {Component, EventEmitter, Output} from '@angular/core';

@Component({
  selector: 'app-filter-input',
  templateUrl: './filter-input.component.html',
  styleUrls: ['./filter-input.component.css']
})
export class FilterInputComponent {
  id!: string;
  @Output() filterEvent: EventEmitter<string> = new EventEmitter<string>();

  filterUsers(){
    this.filterEvent.emit(this.id);
  }
}
