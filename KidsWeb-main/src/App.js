import "./App.css";
import React, { Component } from "react";
import { withNamespaces } from "react-i18next";
import "bootstrap/dist/css/bootstrap.min.css";
import "font-awesome/css/font-awesome.min.css";
import logo from "./assets/images/logo.svg";
import about from "./assets/images/about.svg";
import {
  Button,
  Col,
  Row,
  TabContent,
  TabPane,
  Nav,
  NavItem,
  NavLink,
  Card,
  CardTitle,
  CardText,
  CardBody,
  CardSubtitle,
  Dropdown,
  DropdownMenu,
  DropdownToggle,
} from "reactstrap";
import view from "./assets/images/view.svg";
import headphone from "./assets/images/headphone.svg";
import gift from "./assets/images/gift.svg";
import padlock from "./assets/images/padlock.svg";
import mockup from "./assets/images/Mockup.png";
import playStore from "./assets/images/play-store.svg";
import appStore from "./assets/images/app-store.svg";
import playBlack from "./assets/images/playBlack.svg";
import appBlack from "./assets/images/appleBlack.svg";
import sale from "./assets/images/sale.svg";
import frame from "./assets/images/Frame.svg";
import murod from "./assets/images/murodjon.svg";
import shaxnoza from "./assets/images/shakhnoza.svg";
import pay from "./assets/images/payment.svg";
import payme from "./assets/images/payme.svg";
import click from "./assets/images/click.svg";
import animal from "./assets/images/animal.svg";
import boy from "./assets/images/boy.svg";
import girl from "./assets/images/girl.svg";
import uz_icon from "./assets/images/uzbekistan.png";
import ru_icon from "./assets/images/russia.png";
import CustomizedAccordions from "./components/Accordion";

class App extends Component {
  icons;
  constructor(props) {
    super(props);
    this.toggleDropdown = this.toggleDropdown.bind(this);
    this.state = {
      activeTab: "1",
      dropdownOpen: false,
      current_icon: props.i18n.language,
    };
    this.icons = {
      uz: uz_icon,
      ru: ru_icon,
    };
  }

  toggleDropdown() {
    this.setState({
      dropdownOpen: !this.state.dropdownOpen,
    });
  }
  changeLanguage(lng) {
    this.props.i18n.changeLanguage(lng);
    this.setState((state) => {
      state = {
        ...state,
        current_icon: lng,
      };
      return state;
    });
  }
  render() {
    const { t, i18n } = this.props;

    const toggle = (tab) => {
      if (this.state.activeTab !== tab) this.setState({ activeTab: tab });
    };
    return (
      <div className="container">
        <nav className="navbar navbar-light">
          <ul>
            <li className="navbar-brand" href="#">
              <img src={logo} alt="" />
            </li>
            <li className="menu">
              <a href="#home">{t("home")}</a>
            </li>
            <li className="menu">
              <a href="#download">{t("download")}</a>
            </li>
            <li className="menu">
              <a href="#features">Features</a>
            </li>
            <li className="menu">
              <a href="#pricing">Pricing</a>
            </li>
            <li>
              <a href="#testimonials">Testimonials</a>
            </li>
            <li>
              <a href="#faq">FAQ</a>
            </li>
            <li className="language">
              <Dropdown
                isOpen={this.state.dropdownOpen}
                toggle={this.toggleDropdown}
                className="notification-list"
              >
                <DropdownToggle
                  data-toggle="dropdown"
                  tag="button"
                  className="btn btn-link nav-link dropdown-toggle nav-user mr-0 waves-effect waves-light"
                  onClick={this.toggleDropdown}
                  aria-expanded={this.state.dropdownOpen}
                >
                  <img
                    src={this.icons[this.state.current_icon]}
                    alt=""
                    width="40px"
                  />
                  <span className="pro-user-name ml-1">
                    {" "}
                    <i className="mdi mdi-chevron-down"></i>{" "}
                  </span>
                </DropdownToggle>
                <DropdownMenu className="translate-drop-menu border-0">
                  <div
                    className="row justify-content-center align-items-center"
                    onClick={() => {
                      this.changeLanguage("uz");
                    }}
                  >
                    <img
                      src={uz_icon}
                      className="language_icon mr-1"
                      alt="user"
                    />
                    {/* <span>UZB</span> */}
                  </div>
                  <div
                    className="row justify-content-center align-items-center"
                    onClick={() => {
                      this.changeLanguage("ru");
                    }}
                  >
                    <img
                      src={ru_icon}
                      className="language_icon mr-1"
                      alt="user"
                    />
                    {/* <span>RUS</span> */}
                  </div>
                </DropdownMenu>
              </Dropdown>
            </li>
          </ul>
        </nav>

        {/* home */}

        <Row className="justify-content-between">
          <Col md={4}>
            <h1 id="home" className="home-title">
              Better Monitoring <br />
              Better Control
            </h1>
            <p className="home-text">
              With <a href="">CareKids</a> , you can now manage your kid’s{" "}
              <br /> mobile activites remotely and with proven <br />{" "}
              improvement in behaviour.
            </p>
            <Button className="app-store" color="dark">
              <span>
                <img src={appStore} alt="" />
                App Store
              </span>
            </Button>
            <Button color="dark" className="play-store">
              <span>
                <img src={playStore} alt="" />
              </span>
              <span>Play store</span>
            </Button>
          </Col>
          <Col md={8}>
            <img src={mockup} alt="Rasm yoq" />
          </Col>

          {/* Download */}

          <Col md={4}>
            <img id="download" className="download-image" src={about} alt="" />
          </Col>
          <Col md={8}>
            <h1 className="download-title">All of them in one app</h1>
            <p className="download-description">
              Lorem ipsum dolor sit amet, consectetur adipisicing elit. Fugit
              dolore vero non optio earum numquam cupiditate tempore, doloribus
              odio, nemo molestias? Nisi quas eligendi ipsa mollitia repellat
              eos! Aliquam, perferendis.
              <br /> <br />
              Lorem ipsum dolor, sit amet consectetur adipisicing elit.
              Provident laboriosam pariatur consequatur!
            </p>
            <Button className="download-button">Try it Free</Button>
          </Col>

          {/* Features */}

          <Col id="features" md={12} className="text-center features">
            <h1 className="features-card-menu">Features</h1>
          </Col>
          <Col md={2} className="features-card text-center">
            <img className="features-card-image" src={view} alt="" />

            <h4 className="features-card-title">Activity Monitor</h4>
            <p className="features-card-text">
              Access to all the activity details in your kid’s mobile.
            </p>
          </Col>
          <Col md={2} className="features-card text-center">
            <img className="features-card-image" src={headphone} alt="" />

            <h4 className="features-card-title">Listen surroundings</h4>
            <p className="features-card-text">
              To be calm for your child when they are at school
            </p>
          </Col>
          <Col md={2} className="features-card text-center">
            <img className="features-card-image" src={gift} alt="" />

            <h4 className="features-card-title">Incentives</h4>
            <p className="features-card-text">
              Coins are collected by completing tasks and a chance to win prizes
              at the end of the month
            </p>
          </Col>
          <Col md={2} className="features-card text-center">
            <img className="features-card-image" src={padlock} alt="" />

            <h4 className="features-card-title">App Blocker</h4>
            <p className="features-card-text">
              Find a block that is taking so much of their time? Block it!
            </p>
          </Col>

          {/* payment */}

          <Col md={4}>
            <h1 className="payment-title">
              Opportunity to pay through national payment systems
            </h1>
            <p className="payment-text">
              Lorem ipsum dolor sit amet, consectetur adipiscing elit. Hendrerit
              ut id consequat nunc enim volutpat ultrices nam commodo.
            </p>
            <Button className="payme" color="dark">
              <span>
                <img src={payme} alt="" />
              </span>
            </Button>
            <Button color="dark" className="click">
              <span>
                <img src={click} alt="" />
              </span>
            </Button>
          </Col>
          <Col md={8}>
            <img src={pay} className="pay" alt="Rasm yoq" />
          </Col>

          {/* pricing */}

          <Col md={12} className="text-center">
            <h1 id="pricing" className="pricing">
              Pricing
            </h1>
          </Col>
          <Col md={12}>
            <p className="pricing-description">
              {" "}
              <i class="fa fa-exclamation-circle"></i> Users will be able to use
              our applications for free for <b>2 weeks</b> , and then choose the
              tariff plan that suits them:
            </p>
          </Col>
          <Col md={12} className="text-center">
            <img src={frame} alt="" />
            <img src={sale} alt="" />
          </Col>
          <Col md={12} className="text-center pricing-tab">
            {" "}
            <Nav tabs className="border-0 tab-nav">
              <NavItem>
                <NavLink
                  className="tab-link"
                  onClick={() => {
                    toggle("1");
                  }}
                >
                  Monthly
                </NavLink>
              </NavItem>
              <NavItem>
                <NavLink
                  className={this.state.activeTab == "2" ? "active1" : ""}
                  onClick={() => {
                    toggle("2");
                  }}
                >
                  Yearly
                </NavLink>
              </NavItem>
            </Nav>
          </Col>

          <Col md={12}>
            <TabContent activeTab={this.state.activeTab}>
              <TabPane tabId="1" className="border-0">
                <Row className="justify-content-between p-0">
                  <Col md={3} className="pricing-card">
                    <div className="text-center cardbackImg1">
                      <h5 className="pricing-card-title">Basic</h5>
                      <p className="pricing-card-cost">
                        <b>
                          <sup>$</sup> 2.<sub>55</sub>
                        </b>
                        /oylik
                      </p>
                    </div>
                    <ul>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "green" }}>
                          {" "}
                          <i className="fa fa-check"></i>
                        </span>{" "}
                        Geolakatsiyani bilish
                      </li>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "green" }}>
                          {" "}
                          <i className="fa fa-check"></i>
                        </span>
                        SOS signal qabul qilish
                      </li>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "red" }}>
                          {" "}
                          <i className="fa fa-times"></i>
                        </span>
                        Ilovalarni boshqarish
                      </li>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "red" }}>
                          {" "}
                          <i className="fa fa-times"></i>
                        </span>
                        Yozib olish va atrofdagi audioni yozib olish
                      </li>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "red" }}>
                          {" "}
                          <i className="fa fa-times"></i>
                        </span>
                        Istalgancha bola qo’sha olish
                      </li>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "red" }}>
                          {" "}
                          <i className="fa fa-times"></i>
                        </span>
                        Signal yuborish
                      </li>
                    </ul>
                    <Button color="info" className="pricing-card-button">
                      Get Started
                    </Button>
                  </Col>
                  <Col md={3} className="pricing-card">
                    <div className="text-center cardbackImg2">
                      <h5 className="pricing-card-title">Premium</h5>
                      <p className="pricing-card-cost">
                        <b>
                          <sup>$</sup> 2.<sub>55</sub>
                        </b>
                        /oylik
                      </p>
                    </div>
                    <ul>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "green" }}>
                          {" "}
                          <i className="fa fa-check"></i>
                        </span>{" "}
                        Geolakatsiyani bilish
                      </li>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "green" }}>
                          {" "}
                          <i className="fa fa-check"></i>
                        </span>
                        SOS signal qabul qilish
                      </li>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "green" }}>
                          {" "}
                          <i className="fa fa-check"></i>
                        </span>
                        Ilovalarni boshqarish
                      </li>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "green" }}>
                          {" "}
                          <i className="fa fa-check"></i>
                        </span>
                        Yozib olish va atrofdagi audioni yozib olish
                      </li>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "green" }}>
                          {" "}
                          <i className="fa fa-check"></i>
                        </span>
                        Istalgancha bola qo’sha olish
                      </li>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "green" }}>
                          {" "}
                          <i className="fa fa-check"></i>
                        </span>
                        Signal yuborish
                      </li>
                    </ul>
                    <Button color="info" className="pricing-card-button">
                      Get Started
                    </Button>
                  </Col>
                  <Col md={3} className="pricing-card">
                    <div className="text-center cardbackImg3">
                      <h5 className="pricing-card-title">Standart</h5>
                      <p className="pricing-card-cost">
                        <b>
                          <sup>$</sup> 2.<sub>55</sub>
                        </b>
                        /oylik
                      </p>
                    </div>
                    <ul>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "green" }}>
                          {" "}
                          <i className="fa fa-check"></i>
                        </span>{" "}
                        Geolakatsiyani bilish
                      </li>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "green" }}>
                          {" "}
                          <i className="fa fa-check"></i>
                        </span>
                        SOS signal qabul qilish
                      </li>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "green" }}>
                          {" "}
                          <i className="fa fa-check"></i>
                        </span>
                        Ilovalarni boshqarish
                      </li>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "green" }}>
                          {" "}
                          <i className="fa fa-check"></i>
                        </span>
                        Yozib olish va atrofdagi audioni yozib olish
                      </li>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "red" }}>
                          {" "}
                          <i className="fa fa-times"></i>
                        </span>
                        Istalgancha bola qo’sha olish
                      </li>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "red" }}>
                          {" "}
                          <i className="fa fa-times"></i>
                        </span>
                        Signal yuborish
                      </li>
                    </ul>
                    <Button color="info" className="pricing-card-button">
                      Get Started
                    </Button>
                  </Col>
                </Row>
              </TabPane>
              <TabPane tabId="2">
                <Row className="justify-content-between p-0">
                  <Col md={3} className="pricing-card">
                    <div className="text-center cardbackImg1">
                      <h5 className="pricing-card-title">Basic</h5>
                      <p className="pricing-card-cost">
                        <b>
                          <sup>$</sup> 2.<sub>55</sub>
                        </b>
                        /yillik
                      </p>
                    </div>
                    <ul>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "green" }}>
                          {" "}
                          <i className="fa fa-check"></i>
                        </span>{" "}
                        Geolakatsiyani bilish
                      </li>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "green" }}>
                          {" "}
                          <i className="fa fa-check"></i>
                        </span>
                        SOS signal qabul qilish
                      </li>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "red" }}>
                          {" "}
                          <i className="fa fa-times"></i>
                        </span>
                        Ilovalarni boshqarish
                      </li>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "red" }}>
                          {" "}
                          <i className="fa fa-times"></i>
                        </span>
                        Yozib olish va atrofdagi audioni yozib olish
                      </li>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "red" }}>
                          {" "}
                          <i className="fa fa-times"></i>
                        </span>
                        Istalgancha bola qo’sha olish
                      </li>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "red" }}>
                          {" "}
                          <i className="fa fa-times"></i>
                        </span>
                        Signal yuborish
                      </li>
                    </ul>
                    <Button color="info" className="pricing-card-button">
                      Get Started
                    </Button>
                  </Col>
                  <Col md={3} className="pricing-card">
                    <div className="text-center cardbackImg2">
                      <h5 className="pricing-card-title">Premium</h5>
                      <p className="pricing-card-cost">
                        <b>
                          <sup>$</sup> 2.<sub>55</sub>
                        </b>
                        /yillik
                      </p>
                    </div>
                    <ul>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "green" }}>
                          {" "}
                          <i className="fa fa-check"></i>
                        </span>{" "}
                        Geolakatsiyani bilish
                      </li>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "green" }}>
                          {" "}
                          <i className="fa fa-check"></i>
                        </span>
                        SOS signal qabul qilish
                      </li>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "red" }}>
                          {" "}
                          <i className="fa fa-times"></i>
                        </span>
                        Ilovalarni boshqarish
                      </li>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "red" }}>
                          {" "}
                          <i className="fa fa-times"></i>
                        </span>
                        Yozib olish va atrofdagi audioni yozib olish
                      </li>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "red" }}>
                          {" "}
                          <i className="fa fa-times"></i>
                        </span>
                        Istalgancha bola qo’sha olish
                      </li>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "red" }}>
                          {" "}
                          <i className="fa fa-times"></i>
                        </span>
                        Signal yuborish
                      </li>
                    </ul>
                    <Button color="info" className="pricing-card-button">
                      Get Started
                    </Button>
                  </Col>
                  <Col md={3} className="pricing-card">
                    <div className="text-center cardbackImg3">
                      <h5 className="pricing-card-title">Standart</h5>
                      <p className="pricing-card-cost">
                        <b>
                          <sup>$</sup> 2.<sub>55</sub>
                        </b>
                        /yillik
                      </p>
                    </div>
                    <ul>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "green" }}>
                          {" "}
                          <i className="fa fa-check"></i>
                        </span>{" "}
                        Geolakatsiyani bilish
                      </li>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "green" }}>
                          {" "}
                          <i className="fa fa-check"></i>
                        </span>
                        SOS signal qabul qilish
                      </li>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "red" }}>
                          {" "}
                          <i className="fa fa-times"></i>
                        </span>
                        Ilovalarni boshqarish
                      </li>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "red" }}>
                          {" "}
                          <i className="fa fa-times"></i>
                        </span>
                        Yozib olish va atrofdagi audioni yozib olish
                      </li>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "red" }}>
                          {" "}
                          <i className="fa fa-times"></i>
                        </span>
                        Istalgancha bola qo’sha olish
                      </li>
                      <li className="pricing-card-list">
                        <span style={{ fontSize: "20px", color: "red" }}>
                          {" "}
                          <i className="fa fa-times"></i>
                        </span>
                        Signal yuborish
                      </li>
                    </ul>
                    <Button color="info" className="pricing-card-button">
                      Get Started
                    </Button>
                  </Col>
                </Row>
              </TabPane>
            </TabContent>
          </Col>

          {/* Testimonials */}

          <Col md={12} className="text-center">
            <h1 id="testimonials" className="testimonials">
              Testimonials
            </h1>
          </Col>
          <Col md={5}>
            <Card className="testimonials-card">
              <CardBody>
                <Row className="justify-content-around">
                  <Col md={2}>
                    <img src={shaxnoza} alt="" />
                  </Col>
                  <Col md={10} className="p-3">
                    {" "}
                    <CardTitle tag="h5">Shakhnoza</CardTitle>
                    <CardSubtitle className="mb-2 text-muted" tag="h6">
                      Mom of 6 years old
                    </CardSubtitle>
                  </Col>
                </Row>
                <CardText className="testimonial-card-text">
                  KidsCare has been a really great app and I’ve finally been
                  able to have good control over my kid’s activities
                </CardText>
              </CardBody>
            </Card>
          </Col>
          <Col md={5}>
            <Card className="testimonials-card">
              <CardBody>
                <Row className="justify-content-around">
                  <Col md={2}>
                    <img src={murod} alt="" />
                  </Col>
                  <Col md={10} className="p-3">
                    {" "}
                    <CardTitle tag="h5">Murodjon</CardTitle>
                    <CardSubtitle className="mb-2 text-muted">
                      Father of 8 years old
                    </CardSubtitle>
                  </Col>
                </Row>
                <CardText className="testimonial-card-text">
                  We’ve got our kids activities more managed than ever and all
                  thanks to KidsCare app. Stress free now!
                </CardText>
              </CardBody>
            </Card>
          </Col>
          <Col md={12}>
            <div className="about-app">
              <Row>
                <Col md={6}>
                  <img className="about-app-image" src={animal} alt="" />
                </Col>
                <Col md={6}>
                  <p className="about-app-text1">ABOUT PRODUCT</p>
                  <h2 className="about-app-title">Kidgo mobile app </h2>
                  <p className="about-app-text2">
                    Ilovadan foydalanish orqali farzandingiz o'zi uchun qiziq
                    bo'lgan topshiriqlarni bajarib boradi va oy yakunida
                    kutilmagan sovg'alarga ega bo'ladi. Bu orqali ham bolani
                    nazorat qila olasiz, ham bolangizni fanlarga qiziqtira
                    olasiz.
                  </p>
                </Col>
              </Row>
            </div>
          </Col>

          <Col md={6}>
            <div className="features-app-card-boy">
              <img src={boy} alt="" className="features-app-boy" />
              <p className="features-app-card-title">FEATURES OF aPP</p>
              <p className="features-app-card-text1">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                Hendrerit ut id consequat nunc enim volutpat ultrices nam
                commodo.{" "}
              </p>
              <p className="features-app-card-text2">
                Purus nunc imperdiet a at risus. Non, tristique arcu felis,
                pellentesque mauris.
              </p>
            </div>
          </Col>
          <Col md={6} className="features-diffence">
            <div className="features-app-card-girl">
              <img src={girl} alt="" className="features-app-girl" />
              <p className="features-app-card-title">FEATURES OF aPP</p>
              <p className="features-app-card-text1">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                Hendrerit ut id consequat nunc enim volutpat ultrices nam
                commodo.{" "}
              </p>
              <p className="features-app-card-text2">
                Purus nunc imperdiet a at risus. Non, tristique arcu felis,
                pellentesque mauris.
              </p>
            </div>
          </Col>

          {/* FAQ */}

          <Col md={12} className="text-center">
            <h1 id="faq" className="faq">
              FAQ
            </h1>
          </Col>
          <Col md={12}>
            <CustomizedAccordions />
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
          </Col>
          <Col md={12} className="footer-sector position-relative">
            <div className="yellow-circle"></div>
            <div className="green-circle"></div>
            <div className="primary-circle"></div>
            <h1 className="footer-sector-text">
              Did you like the mobile app? Download now!
            </h1>

            <Button className="footer-sector-app-store">
              <span>
                <img src={appBlack} alt="" />
                App Store
              </span>
            </Button>
            <Button className="footer-sector-play-store">
              <span>
                <img src={playBlack} alt="" />
              </span>
              <span>Play store</span>
            </Button>
          </Col>
          <hr />

          {/* footer */}

          <Col md={12}>
            <Row>
              <Col md={1}>
                <img src={logo} alt="" />
              </Col>
              <Col md={8}>
                <ul className="footer-nav">
                  <li className="footer-nav-li">
                    <a href="">Home</a>
                  </li>
                  <li className="footer-nav-li">
                    <a href="">Download</a>
                  </li>
                  <li className="footer-nav-li">
                    <a href="">Featurtes</a>
                  </li>
                  <li className="footer-nav-li">
                    <a href="">Pricing</a>
                  </li>
                  <li className="footer-nav-li">
                    <a href="">Testimonials</a>
                  </li>
                  <li className="footer-nav-li">
                    <a href="">FAQ</a>
                  </li>
                </ul>
              </Col>
              <Col md={3}>
                <ul>
                  <li className="footer-sector-icon">
                    <a href="">
                      <i className="fa fa-facebook fa-2x"></i>
                    </a>
                  </li>
                  <li className="footer-sector-icon">
                    <a href="#">
                      <i className="fa fa-twitter fa-2x"></i>
                    </a>
                  </li>
                  <li className="footer-sector-icon">
                    <a href="#">
                      <i className="fa fa-instagram fa-2x"></i>
                    </a>
                  </li>
                </ul>
              </Col>
            </Row>
          </Col>
        </Row>
      </div>
    );
  }
}
export default withNamespaces()(App);
