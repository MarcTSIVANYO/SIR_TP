import {Component, Input, OnInit} from '@angular/core';
import {KanbanShareInfos} from '../../service/kanban-share-infos';
import {Section} from '../../model/section.model';
import {KanbanService} from '../../service/kanban.service';
import {ActivatedRoute} from '@angular/router';
import {Fiche} from '../../model/fiche.model';
import {SectionService} from '../../service/section.service';

@Component({
  selector: 'app-kanbanbord',
  templateUrl: './kanbanbord.component.html',
  styleUrls: ['./kanbanbord.component.css']
})
export class KanbanbordComponent implements OnInit {
  @Input() sections: Section[] = [];
  sectionsEnAttente: Section;
  sectionsEnCours: Section;
  sectionsExecute: Section;
  fichesEnAttente: Fiche[] = [];
  fichesEnCours: Fiche[] = [];
  fichesExecute: Fiche[] = [];
  constructor(private kanbanService: KanbanService, private route: ActivatedRoute,private sectionService: SectionService) {
  }

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    console.log('SADDD ' + id);
    this.kanbanService.getKanbanSectionById(+id).subscribe(
      (response: Section[]) => {
        this.sections = response;
        this.filter(this.sections);
      }
    );
  }
  filter(sections: Section[]): void {
    this.sectionsEnAttente = sections.find(section => section.libelle === 'En attente');
    console.log('BAR', this.sectionsEnAttente);
    this.getFichesAttente(this.sectionsEnAttente.id);
    this.sectionsEnCours = sections.find(section => section.libelle === 'En cours');
    console.log('BAR', this.sectionsEnCours);
    this.getFichesEnCours(this.sectionsEnCours.id);
    this.sectionsExecute = sections.find(section => section.libelle === 'Execute');
    console.log('BAR', this.sectionsExecute);
    this.getFichesExecute(this.sectionsExecute.id);
  }

  getFichesAttente(id: number): void {
    this.sectionService.getSectionFiches(id).subscribe(
      (response: Fiche[]) => {
        this.fichesEnAttente = response;
      }
    );
  }

  getFichesEnCours(id: number): void {
    this.sectionService.getSectionFiches(id).subscribe(
      (response: Fiche[]) => {
        this.fichesEnCours = response;
      }
    );
  }

  getFichesExecute(id: number): void {
    this.sectionService.getSectionFiches(id).subscribe(
      (response: Fiche[]) => {
        this.fichesExecute = response;
      }
    );
  }
}
