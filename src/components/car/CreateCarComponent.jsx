import React, { Component } from 'react'
import CarServices from './services/CarServices';

class CreateCarComponent extends Component {

    constructor(props){
        super(props)
            this.state={
                // step 2
                id:this.props.match.params.id,
                Brand:'',
                Model:'',
                Variant:'',
                registrationYear:'',
                registrationState:'',
           }
           this.changeBrandHandler=this.changeBrandHandler.bind(this);
           this.changeModelHandler=this.changeModelHandler.bind(this);
           this.changeVariantHandler=this.changeVariantHandler.bind(this);
           this.changeRegistrationYearHandler=this.changeRegistrationYearHandler.bind(this);
           this.changeRegistrationStateHandler=this.changeRegistrationStateHandler.bind(this);
          this.saveOrUpdateCar=this.saveOrUpdateCar.bind(this);
        }
         
         //step 3
         componentDidMount(){
             //step 4
             if(this.state.id === 'addCar'){
                 return
             }else{
            
                    CarServices.getCarById(this.state.id).then((res) =>{
                        let car =res.data;
                        this.setState({brand: car.Brand,
                            model: car.Model,
                            variant: car.Variant,
                            registrationyear: car.registrationYear,
                            registrationstate: car.registrationState
                        
                            
                        });
                    });
                }
             }
          

        saveOrUpdateCar=(e) =>{
            e.preventDefault();
            let car={brand:this.state.brand,model:this.state.model,variant:this.state.variant,registrationyear:this.state.registrationyear,registrationstate:this.state.registrationstate};
            console.log('car=>'+ JSON.stringify(car));

            // step 5
            if(this.state.id === -1){
                CarServices.createCar(car).then(res =>{
                    this.props.history.push('/car');
                });
            }else{
                CarServices.updateCar(car,this.state.id).then(res=>{
                    this.props.history.push('/car');
            

            });
        }
    }
        changeBrandHandler=(event) =>{
            this.setState({Brand:event.target.value});
        }

        changeModelHandler=(event) =>{
            this.setState({Model:event.target.value});
        }

        changeVariantHandler=(event) =>{
            this.setState({Variant:event.target.value});
        }

        changeRegistrationYearHandler=(event) =>{
            this.setState({registrationYear:event.target.value});
        }

        changeRegistrationStateHandler=(event) =>{
            this.setState({registrationState:event.target.value});
        }
        cancel(){
            this.props.history.push('/car');
        }
        gettitle(){
            if(this.state.id === 'addCar'){
                return  <h3 className="text-center">Add Car</h3>
            }else{
                return  <h3 className="text-center">Update Car</h3>
            }

        }

    render() {
        return (
            <div>
               <div className="container">
                   <div className="row">
                       <div className="card col-md-6 offset-md-3 offset-md-3">
                           {
                               this.gettitle()
                           }
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
                                       <input placeholder="Variant Name" name="variant" className="form-control" 
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
                                   <button className="btn btn-success" onClick={this.saveOrUpdateCar}>Save</button>
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
 export default CreateCarComponent