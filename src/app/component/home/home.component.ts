import { Component, OnInit } from '@angular/core';
import {KanbanService} from '../../service/kanban.service';
import {Kanban} from '../../model/kanban.model';
import {Subscription} from 'rxjs';
import {KanbanShareInfos} from '../../service/kanban-share-infos';
import {Router} from '@angular/router';
import {Section} from '../../model/section.model';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  kanbans: Kanban[] = [];
  sections: Section[] = [];
  kanbanSubscription: Subscription;
  addKanbanForm: FormGroup;
  constructor(private kanbanService: KanbanService, private kanbanShareInfos: KanbanShareInfos, private route: Router,
              private formBuilder: FormBuilder, private modalService: NgbModal) {
    this.kanbanSubscription = new Subscription();
  }

  ngOnInit(): void {
    this.initFomulaire();
    this.kanbanSubscription = this.kanbanService.kanbansSubject.subscribe(
      (response: Kanban[]) => {
        this.kanbans = response;
      }
    );
    this.kanbanService.getAll();
  }

  initFomulaire(): void {
    this.addKanbanForm = this.formBuilder.group({
      kanbanName: ['', Validators.required]
    });
  }

  openModal(targetModal): void {
    this.modalService.open(targetModal, {
      centered: true,
      backdrop: 'static'
    });
  }

  getKanbanId(kanbanId: number): void{
    if (kanbanId !== null){
      this.kanbanService.getKanbanSectionById(kanbanId).subscribe(
        (reponse: Section[]) => { this.sections = reponse; }
      );
      this.route.navigate(['kanban']);
    }
  }

  onSubmit(): void {
    this.modalService.dismissAll();
    const name = this.addKanbanForm.value.kanbanName;
    const newKanban: Kanban = {
      id: 0,
      nom: name,
      admin: {
        id: 1,
        email: '',
        name: '',
        password: null
      }
    };
    console.log(newKanban);
    this.kanbanService.createKanban(newKanban);
  }

}
