import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KanbanbordComponent } from './kanbanbord.component';

describe('KanbanbordComponent', () => {
  let component: KanbanbordComponent;
  let fixture: ComponentFixture<KanbanbordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KanbanbordComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KanbanbordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
