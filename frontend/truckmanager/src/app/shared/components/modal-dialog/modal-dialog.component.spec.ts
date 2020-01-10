import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalDialogComponent } from './modal-dialog.component';
import { SharedModule } from '../../shared.module';

describe('ModalDialogComponent', () => {
  let component: ModalDialogComponent;
  let fixture: ComponentFixture<ModalDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [SharedModule]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should emit true', () => {
    spyOn(component.submit, 'emit');

    component.onClick(true);

    expect(component.submit.emit).toHaveBeenCalledWith(true);
    expect(component.submit.emit).toHaveBeenCalledTimes(1);
  });

  it('should emit false', () => {
    spyOn(component.submit, 'emit');

    component.onClick(false);

    expect(component.submit.emit).toHaveBeenCalledWith(false);
    expect(component.submit.emit).toHaveBeenCalledTimes(1);
  });
});
