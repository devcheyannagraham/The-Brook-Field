import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicationItemFormComponent } from './publication-item-form.component';

describe('PublicationItemFormComponent', () => {
  let component: PublicationItemFormComponent;
  let fixture: ComponentFixture<PublicationItemFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PublicationItemFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PublicationItemFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
