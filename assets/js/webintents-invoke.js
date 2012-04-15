alert('start');
function Intent(action, type) {
  this.action = action;
  this.type = type;
}
function Navigator() {}
Navigator.prototype.startActivity = function(intent) {
  WIService.startActivity(intent.action, intent.type);
};
window.navigator = new Navigator();
WIService.alert("test_wi");