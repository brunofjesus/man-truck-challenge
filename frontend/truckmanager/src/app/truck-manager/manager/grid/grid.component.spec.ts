import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GridComponent } from './grid.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { TextItemComponent } from '../detail/text-item/text-item.component';
import { ColorItemComponent } from '../detail/color-item/color-item.component';
import { RouterTestingModule } from '@angular/router/testing';
import { StoreModule } from '@ngrx/store';
import { TruckReducer } from 'src/app/store/reducers/truck.reducer';

describe('GridComponent', () => {
  let component: GridComponent;
  let fixture: ComponentFixture<GridComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [GridComponent, TextItemComponent, ColorItemComponent],
      imports: [SharedModule, RouterTestingModule,
        StoreModule.forRoot({
          truck: TruckReducer
        })
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should emit', () => {
    spyOn(component.clickEvent, 'emit');

    component.click(20);

    expect(component.clickEvent.emit).toHaveBeenCalledWith(20);
    expect(component.clickEvent.emit).toHaveBeenCalledTimes(1);
  });

});
