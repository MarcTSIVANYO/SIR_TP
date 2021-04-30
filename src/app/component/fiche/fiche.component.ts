import {Component, Input, OnInit} from '@angular/core';
import {User} from '../../model/user.model';
import {Fiche} from '../../model/fiche.model';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {FicheService} from '../../service/fiche.service';
import {SectionService} from '../../service/section.service';

@Component({
  selector: 'app-fiche',
  templateUrl: './fiche.component.html',
  styleUrls: ['./fiche.component.css']
})
export class FicheComponent implements OnInit {

  @Input() fiche: Fiche;
  editFicheForm: FormGroup;
  constructor(private formBuilder: FormBuilder, private modalService: NgbModal, private ficheService: FicheService, private sectionService: SectionService) { }

  ngOnInit(): void {
    this.initFomulaire();
  }

  initFomulaire(): void {
    this.editFicheForm = this.formBuilder.group({
      libelle: ['', Validators.required],
      lieu: ['', Validators.required],
      url: ['', Validators.required],
      datebutoire: ['', Validators.required],
      note: ['', Validators.required]
    });
  }
  onEditFiche(targetModal): void {
    this.modalService.open(targetModal, {
      centered: true,
      backdrop: 'static'
    });

    this.editFicheForm.patchValue({
      libelle: this.fiche.libelle,
      lieu: this.fiche.lieu,
      url: this.fiche.url,
      datebutoire: this.fiche.dateButoire,
      note: this.fiche.note,
    });
  }

  onSubmit(): void {
    this.modalService.dismissAll();
    this.fiche.libelle = this.editFicheForm.value.libelle;
    this.fiche.lieu = this.editFicheForm.value.lieu;
    this.fiche.url = this.editFicheForm.value.url;
    this.fiche.dateButoire = new Date();
    this.fiche.note = this.editFicheForm.value.note;
    this.fiche.owner = new User(1, 'scr.master@gmail.com', '', '');
    this.fiche.tags = [];

    this.ficheService.updateFiche(this.fiche, this.fiche.id);
    this.sectionService.getAll();
  }
}
