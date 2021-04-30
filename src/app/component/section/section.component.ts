import {Component, Input, OnInit} from '@angular/core';
import {Section} from '../../model/section.model';
import {SectionService} from '../../service/section.service';
import {Fiche} from '../../model/fiche.model';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {FicheService} from '../../service/fiche.service';
import {User} from '../../model/user.model';

@Component({
  selector: 'app-section',
  templateUrl: './section.component.html',
  styleUrls: ['./section.component.css']
})
export class SectionComponent implements OnInit {
  @Input() section: Section;
  @Input() fiches: Fiche[] = [];
  @Input() libelle = '';
  addFicheForm: FormGroup;
  idSection: number;
  constructor(private formBuilder: FormBuilder, private modalService: NgbModal, private ficheService: FicheService, private sectionService: SectionService) { }

  ngOnInit(): void {
  this.initFomulaire();
  }

  initFomulaire(): void {
    this.addFicheForm = this.formBuilder.group({
      libelle: ['', Validators.required],
      lieu: ['', Validators.required],
      url: ['', Validators.required],
      datebutoire: ['', Validators.required],
      note: ['', Validators.required]
    });
  }


  onAddFiche(targetModal, sec: Section): void {
    this.idSection = sec.id;
    console.log(this.idSection);
    this.modalService.open(targetModal, {
      centered: true,
      backdrop: 'static'
    });
  }

  onSubmit(): void{
    this.modalService.dismissAll();
    const libelle = this.addFicheForm.value.libelle;
    const lieu = this.addFicheForm.value.lieu;
    const url = this.addFicheForm.value.url;
    const datebutoire = new Date();
    const note = this.addFicheForm.value.note;
    const user = new User(1, 'scr.master@gmail.com', '', '');
    const fiche: Fiche = {
      id: 0,
      libelle,
      lieu,
      url,
      dateButoire: datebutoire,
      note,
      tags: [],
      owner: user,
      dureeminite: 0,
      sectionId: this.idSection
    };
    this.ficheService.createFiche(fiche);
    this.sectionService.getAll();
  }
}
