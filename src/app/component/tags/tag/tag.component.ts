import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TagService } from 'src/app/service/tag.service';
import {Subscription} from 'rxjs';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { Tag } from 'src/app/model/tag';
import { ConfirmationDialogService } from 'src/app/service/confirmation-dialog.service';

@Component({
  selector: 'app-tag',
  templateUrl: './tag.component.html',
  styleUrls: ['./tag.component.css']
})
export class TagComponent implements OnInit {
  title:String='Liste des tags'
  headers=['Id', 'Titre', 'Actions']
  tags: Tag[] = [];
  tagSubscription: Subscription;
  addTagForm: FormGroup;
  currentTag :any= null;
  currentIndex = -1;
  message = '';
  fieldTextType = false;
  constructor(private tagService: TagService, private route: ActivatedRoute, private confirmationDialogService: ConfirmationDialogService,
              private formBuilder: FormBuilder, private modalService: NgbModal) {
    this.tagSubscription = new Subscription();
  }
  ngOnInit(): void {
    this.message = '';
    this.initFomulaire();

    this.tagSubscription = this.tagService.tagsSubject.subscribe(
      (response: Tag[]) => {
        this.tags = response;
      }
    );
    this.reloadData();

  }

  reloadData(): void {
    this.tagService.getTags();
    this.tags = this.tagService.tags;
    console.log(this.tagService.tags);
  }

  refreshList(): void {
    this.reloadData();
    this.currentTag = null;
    this.currentIndex = -1;
  }

  setActiveTag(tag:any, index:number): void {
    this.currentTag = tag;
    this.currentIndex = index;
  }

  updateTag(): void {
    this.tagService.update(this.currentTag.id, this.currentTag)
      .subscribe(
        response => {
          console.log(response);
          this.message = 'Tag modifé avec succès!';
        },
        error => {
          console.log(error);
        });
  }

  deleteTag(): void {
    this.confirmationDialogService.confirm('Confirmation', 'Voulez-vous vraiment supprimer cet tag?')
    .then((confirmed) =>  {
      if(confirmed){
      this.tagService.delete(this.currentTag.id)
      .subscribe(
        response => {
          console.log(response);
          this.reloadData();
          this.message = 'Tag supprimé avec succès!';
          this.currentTag = null;
         // this.router.navigate(['/tags']);
        },
        error => {
          console.log(error);
        });
    }
  }).catch(() => console.log("L'tag a fermé la boîte de dialogue (par exemple, en utilisant ESC, en cliquant sur l'icône en forme de croix ou en cliquant en dehors de la boîte de dialogue)"));
  }

  toggleFieldTextType(): void {
    this.fieldTextType = !this.fieldTextType;
  }

  onSubmit(): void {
    this.modalService.dismissAll();
    this.savetag();
  }

  initFomulaire(): void {
    this.addTagForm = this.formBuilder.group({
      name: ['', Validators.required]    });
  }
  openModal(addTagModal): void {
    this.modalService.open(addTagModal, {
      centered: true,
      backdrop: 'static'
    });
  }

  savetag(): void {
      const data: Tag = {
        id: 0,
        name: this.addTagForm.value.name
      };

      this.tagService.create(data);
  }

}
