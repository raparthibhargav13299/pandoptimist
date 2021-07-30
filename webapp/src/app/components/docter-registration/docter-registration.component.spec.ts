import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { By } from '@angular/platform-browser';
import { CKEditorModule } from '@ckeditor/ckeditor5-angular';

import { DocterRegistrationComponent } from './docter-registration.component';

describe('DocterRegistrationComponent', () => {
  let component: DocterRegistrationComponent;
  let fixture: ComponentFixture<DocterRegistrationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DocterRegistrationComponent ]
      // imports: [ CKEditorModule, FormsModule, ReactiveFormsModule ]
    })
    .compileComponents();
  });

  afterEach( () => {
		fixture.destroy();
	} );

  beforeEach(() => {
    fixture = TestBed.createComponent(DocterRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });


  it( 'should log the model to the console when user submits the form', () => {
		const spy = spyOn( console, 'log' );

		const submitButton: HTMLButtonElement = fixture.debugElement.query( By.css( 'button[type=submit]' ) ).nativeElement;
		submitButton.click();

		expect( spy ).toHaveBeenCalledTimes( 1 );
		expect( spy.calls.first().args ).toEqual( jasmine.arrayContaining( [
			'Form submit, model',
			jasmine.objectContaining( {
				name: 'John',
				surname: 'Doe',
				description: '<p>A <b>really</b> nice fellow.</p>'
			} )
		] ) );
	} );

	it( 'should reset form after clicking the reset button', done => {
		setTimeout( () => {
			const resetButton: HTMLButtonElement = fixture.debugElement.query( By.css( 'button[type=reset]' ) ).nativeElement;
			resetButton.click();

			fixture.detectChanges();

			// expect( component.formDataPreview ).toEqual( '{"name":null,"surname":null,"description":null}' );

			done();
		} );
	} );


});
