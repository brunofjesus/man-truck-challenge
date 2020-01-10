import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateEditComponent } from './create-edit.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { StoreModule } from '@ngrx/store';
import { TruckReducer } from 'src/app/store/reducers/truck.reducer';
import { PropertiesReducer } from 'src/app/store/reducers/properties.reducer';
import { TruckEffects } from 'src/app/store/effects/truck.effects';
import { EffectsModule } from '@ngrx/effects';
import { TrucksService } from 'src/swagger';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';


describe('CreateEditComponent', () => {
  let component: CreateEditComponent;
  let fixture: ComponentFixture<CreateEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [CreateEditComponent],
      imports: [
        SharedModule,
        FormsModule,
        RouterTestingModule,
        HttpClientTestingModule,
        NoopAnimationsModule,

        StoreModule.forRoot({
          truck: TruckReducer,
          properties: PropertiesReducer
        }),
        EffectsModule.forRoot([
          TruckEffects,
        ])
      ],
      providers: [TrucksService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
