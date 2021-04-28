import { Component, OnInit } from '@angular/core';
import {KanbanService} from '../../service/kanban.service';
import {Kanban} from '../../model/kanban.model';
import {Subscription} from 'rxjs';
import {KanbanShareInfos} from '../../service/kanban-share-infos';
import {Router} from '@angular/router';
import {Section} from '../../model/section.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  kanbans: Kanban[] = [];
  sections: Section[] = [];
  kanbanSubscription: Subscription;
  constructor(private kanbanService: KanbanService, private kanbanShareInfos: KanbanShareInfos, private route: Router) {
    this.kanbanSubscription = new Subscription();
  }

  ngOnInit(): void {
    this.kanbanSubscription = this.kanbanService.kanbansSubject.subscribe(
      (response: Kanban[]) => {
        this.kanbans = response;
      }
    );
    this.kanbanService.getAll();
  }

  getKanbanId(kanbanId: number): void{
    if (kanbanId !== null){
      this.kanbanService.getKanbanSectionById(kanbanId).subscribe(
        (reponse: Section[]) => { this.sections = reponse; }
      );
      this.route.navigate(['kanban']);
    }
  }

}
