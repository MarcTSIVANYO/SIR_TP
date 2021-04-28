import {Component, Input, OnInit} from '@angular/core';
import {KanbanShareInfos} from '../../service/kanban-share-infos';
import {Section} from '../../model/section.model';
import {KanbanService} from '../../service/kanban.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-kanbanbord',
  templateUrl: './kanbanbord.component.html',
  styleUrls: ['./kanbanbord.component.css']
})
export class KanbanbordComponent implements OnInit {
  @Input() sections: Section[] = [];
  sectionsEnAttente: Section[] = [];
  sectionsEnCours: Section[] = [];
  sectionsExecute: Section[] = [];
  constructor(private kanbanService: KanbanService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.kanbanService.getKanbanSectionById(+id).subscribe(
      (response: Section[]) => {
        this.sections = response;
        this.filter(this.sections);
      }
    );
  }

  filter(sections: Section[]): void {
    this.sectionsEnAttente = sections.filter(section => section.libelle === 'En attente');
    console.log('Attente', this.sectionsEnAttente);
    this.sectionsEnCours = sections.filter(section => section.libelle === 'En cours');
    console.log('En cours', this.sectionsEnCours);
    this.sectionsExecute = sections.filter(section => section.libelle === 'Execute');
    console.log('Execute', this.sectionsExecute);
  }

}
