import {Component, Input, OnInit} from '@angular/core';
import {Section} from '../../model/section.model';

@Component({
  selector: 'app-section',
  templateUrl: './section.component.html',
  styleUrls: ['./section.component.css']
})
export class SectionComponent implements OnInit {
  @Input() section: Section = new Section(0,'',0);
  constructor() { }

  ngOnInit(): void {
  }

}
