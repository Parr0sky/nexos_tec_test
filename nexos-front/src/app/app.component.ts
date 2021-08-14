import { Component, OnInit, TemplateRef } from '@angular/core';
import {DataService} from './data.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'nexos-front';
  // @ts-ignore

  // tslint:disable-next-line:variable-name
  constructor(private _ds: DataService) {
  }


  ngOnInit(): void {
  }
}
