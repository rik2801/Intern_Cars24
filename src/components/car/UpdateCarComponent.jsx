import React, { Component } from 'react'
import CarServices from '../services/CarServices';

class UpdateCarComponent extends Component {
    constructor(props){
        super(props)
            this.state={
                // step 2
                id:this.props.match.params.id,
                Brand:'',
                Model:'',
                Variant:'',
                registrationYear:'',
                registrationState:''
           }
           this.changeBrandHandler=this.changeBrandHandler.bind(this);
           this.changeModelHandler=this.changeModelHandler.bind(this);
           this.changeVariantHandler=this.changeVariantHandler.bind(this);
           this.changeRegistrationYearHandler=this.changeRegistrationYearHandler.bind(this);
           this.changeRegistrationStateHandler=this.changeRegistrationStateHandler.bind(this);
          this.updateCar=this.updateCar.bind(this);
        }

        componentDidMount(){
            CarServices.getCarById(this.state.id).then((res) =>{
                let car =res.data;
                this.setState({brand: car.brand,
                    model: car.model,
                    variant: car.variant,
                    registrationyear: car.registrationyear,
                    registrationstate: car.registrationstate
                
                    
                });
            });
        }
        updateCar=(e) =>{
            e.preventDefault();
            let car={Brand:this.state.Brand,Model:this.state.Model,Variant:this.state.Variant,registrationYear:this.state.registrationYear,registrationState:this.state.registrationstate};
            console.log('car=>'+ JSON.stringify(car));
          CarServices.updateCar(car,this.state.id).then(res=>{
              this.props.history.push('/car');
          });
           }
        changeBrandHandler=(event) =>{
            this.setState({brand:event.target.value});
        }

        changeModelHandler=(event) =>{
            this.setState({model:event.target.value});
        }

        changeVariantHandler=(event) =>{
            this.setState({variant:event.target.value});
        }

        changeRegistrationYearHandler=(event) =>{
            this.setState({registrationyear:event.target.value});
        }

        changeRegistrationStateHandler=(event) =>{
            this.setState({registrationstate:event.target.value});
        }
        cancel(){
            this.props.history.push('/car');
        }


    render() {
        return (
            <div>
               <div className="container">
                   <div className="row">
                       <div className="card col-md-6 offset-md-3 offset-md-3">
                           <h3 className="text-center">Update Car</h3>
                           <div className="card-body">
                               <form>
                                   <div className="form-group">
                                       <label>Brand</label>
                                       <input placeholder="Brand Name" name="Brand" className="form-control" 
                                        value={this.state.Brand} onChange={this.changeBrandHandler}/>
                                       
                                   </div>
                                   <div className="form-group">
                                       <label>Model</label>
                                       <input placeholder="Model Name" name="Model" className="form-control" 
                                        value={this.state.Model} onChange={this.changeModelHandler}/>
                                       
                                   </div>
                                   <div className="form-group">
                                       <label>Variant</label>
                                       <input placeholder="Variant Name" name="Variant" className="form-control" 
                                        value={this.state.Variant} onChange={this.changeVariantHandler}/>
                                       
                                   </div>
                                   <div className="form-group">
                                       <label> Registration year</label>
                                       <input placeholder="Registration year" name="registrationYear" className="form-control" 
                                        value={this.state.registrationYear} onChange={this.changeRegistrationYearHandler}/>
                                       
                                   </div>
                                   <div className="form-group">
                                       <label>Registration state</label>
                                       <input placeholder="Registration state" name="registrationState" className="form-control" 
                                        value={this.state.registrationState} onChange={this.changeRegistrationStateHandler}/>
                                       
                                   </div>
                                   <button className="btn btn-success" onClick={this.updateCar}>Save</button>
                                   <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft:"10px"}}>Cancel</button>

                               </form>
                           </div>
                       </div>
                   </div>
               </div>

            </div>
        )
    }
}
export default UpdateCarComponent;
