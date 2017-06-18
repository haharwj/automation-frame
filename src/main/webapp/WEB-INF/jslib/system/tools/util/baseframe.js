/**
 * 系统引导类
 * 
 * @Author Sirius Zhang in 2015.05.01
 */
/**
 * 
 * 系统必备操作
 */
var __debug = __debug == undefined ? false : __debug;
System = {
    running : 0,
    lock : false
};

// 系统操作简写
S = {};

System.blank = function () {
};
System.init = function () {
	return "jslib/system/";
};
System.init.modulePath = function () {
	return System.init () + "module";
};

System.init.toolsPath = function () {
	return System.init () + "tools";
};
System.init.loadJS = function (jsfile, callback) {
	// document.write('<script type="text/javascript" src="' +
	// System.init.modulePath() + jsfile
	// + '"></script>');
	if (__debug == undefined || !__debug){
		System.running++;
		$.getScript (System.init.modulePath () + jsfile).complete (function () {
			System.running--;
			if (callback){
				callback ();
			}
			LoadProgress.load (75, jsfile);
		});
	}
	else{
		document.write ('<script src="' + System.init.modulePath () + jsfile + '"></script>');
		LoadProgress.load (75, jsfile);
	}
};
System.init.loadSystemJS = function (jsfile, callback) {
	// document.write('<script type="text/javascript" src="'
	// + System.init.toolsPath() + jsfile + '"></script>');
	if (__debug == undefined || !__debug){
		System.running++;
		$.getScript (System.init.toolsPath () + jsfile).complete (function () {
			System.running--;
			if (callback){
				callback ();
			}
			LoadProgress.load (75, jsfile);
		});
	}
	else{
		document.write ('<script src="' + System.init.toolsPath () + jsfile + '"></script>');
		LoadProgress.load (75, jsfile);
	}
};

System.init.loadScript = function (jsfile, callback) {
	// document.write('<script type="text/javascript" src="' + jsfile
	// + '"></script>');
	if (__debug == undefined || !__debug){
		System.running++;
		$.getScript (jsfile).complete (function () {
			try{
				console.log ("Load Script " + jsfile);
			}
			catch (e){
				
			}
			System.running--;
			if (callback){
				callback ();
			}
		});
	}
	else{
		document.write ('<script src="' + jsfile + '"></script>');
		LoadProgress.load (75, jsfile);
	}
};

System.OS = function () {
};
System.Window = function () {
};
System.Window.beforeunload = function () {
	var ntko = new Ntko.document.word ();
	// 关闭文档
	ntko.closeDocument ({
		id : '_ntko_main_ocx'
	});
	ntko.destoryDocument ({
		id : '_document_main'
	});
};
System.Grid = function () {
};
System.Message = function () {
};

System.OS.navigator = function () {
	if (navigator == undefined){
		return "MSIE";
	}
	else if (navigator.userAgent.indexOf ("MSIE") > 0){
		return "MSIE";
	}
	
	else if (isFirefox = navigator.userAgent.indexOf ("Firefox") > 0){
		return "Firefox";
	}
	
	else if (isSafari = navigator.userAgent.indexOf ("Safari") > 0){
		return "Safari";
	}
	
	else if (isCamino = navigator.userAgent.indexOf ("Camino") > 0){
		return "Camino";
	}
	
	else if (isMozilla = navigator.userAgent.indexOf ("Gecko/") > 0){
		return "Gecko";
	}
	else{
		return "MSIE";
	}
	
};

System.Wait = function (id) {
	var mask = new Ext.LoadMask (id, {
	    msg : "加载中， 请稍后...",
	    removeMask : true
	});
	return mask;
};

System.WaitNoMessage = function (id) {
	var mask = new Ext.LoadMask (id, {
		removeMask : true
	});
	return mask;
};

System.Wait.Sleep = function (obj, iMinSecond) {
	if (window.eventList == null)
		window.eventList = new Array ();
	var ind = -1;
	for ( var i = 0; i < window.eventList.length; i++){
		if (window.eventList[i] == null){
			window.eventList[i] = obj;
			ind = i;
			break;
		}
	}
	if (ind == -1){
		ind = window.eventList.length;
		window.eventList[ind] = obj;
	}
	setTimeout ("System.Wait.Sleep.Time(" + ind + ")", iMinSecond);
};

System.Wait.Sleep.Time = function (ind) {
	var obj = window.eventList[ind];
	window.eventList[ind] = null;
	if (obj.NextStep)
		obj.NextStep ();
	else
		obj ();
};

System.Window.unmask = function () {
	Ext.getBody ().unmask ();
};
System.Window.mask = function () {
	Ext.getBody ().mask ();
};
System.Window.maskId = function (id) {
	Ext.getCmp (id).getEl ().mask ();
};
System.Window.unmaskId = function (id) {
	Ext.getCmp (id).getEl ().unmask ();
};

System.Window.width = function () {
	var winWidth = 0;
	if (window.innerWidth)
		winWidth = window.innerWidth;
	else if ((document.body) && (document.body.clientWidth))
		winWidth = document.body.clientWidth;
	if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth){
		winWidth = document.documentElement.clientWidth;
	}
	return winWidth;
};

System.Window.height = function () {
	var winHeight = 0;
	if (window.innerHeight)
		winHeight = window.innerHeight;
	else if ((document.body) && (document.body.clientHeight))
		winHeight = document.body.clientHeight;
	if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth){
		winHeight = document.documentElement.clientHeight;
	}
	return winHeight;
};

// Map对象定义
System.Map = function () {
	this.container = {};
};
// 将key-value放入map中
System.Map.prototype.put = function (key, value) {
	try{
		if (key != null && key != "")
			this.container[key] = value;
	}
	catch (e){
		return e;
	}
};

// 根据key从map中取出对应的value
System.Map.prototype.get = function (key) {
	try{
		
		return this.container[key];
		
	}
	catch (e){
		return e;
	}
};

// 判断map中是否包含指定的key
System.Map.prototype.containsKey = function (key) {
	try{
		for ( var p in this.container){
			if (p == key)
				return true;
		}
		
		return false;
		
	}
	catch (e){
		return e;
	}
	
};
// 判断map中是否包含指定的value
System.Map.prototype.containsValue = function (value) {
	try{
		
		for ( var p in this.container){
			if (this.container[p] === value)
				return true;
		}
		
		return false;
		
	}
	catch (e){
		return e;
	}
};

// 删除map中指定的key
System.Map.prototype.remove = function (key) {
	try{
		
		delete this.container[key];
		
	}
	catch (e){
		return e;
	}
};

// 清空map
System.Map.prototype.clear = function () {
	try{
		delete this.container;
		this.container = {};
		
	}
	catch (e){
		return e;
	}
};

// 判断map是否为空
System.Map.prototype.isEmpty = function () {
	if (this.keyArray ().length == 0)
		return true;
	else
		return false;
};

// 获取map的大小
System.Map.prototype.size = function () {
	return this.keyArray ().length;
};

// 返回map中的key值数组
System.Map.prototype.keyArray = function () {
	var keys = new Array ();
	for ( var p in this.container){
		keys.push (p);
	}
	return keys;
};

// 返回map中的value值数组
System.Map.prototype.valueArray = function () {
	var values = new Array ();
	var keys = this.keyArray ();
	for ( var i = 0; i < keys.length; i++){
		values.push (this.container[keys[i]]);
	}
	return values;
};

// 复制map对象
System.Map.prototype.clone = function () {
	if (this.container == null)
		return new System.Map ();
	var array = this.keyArray ();
	var result = new System.Map ();
	for ( var i = 0; i < array.length; i++){
		var key = array[i];
		var obj = this.get (key);
		result.put (key, obj);
	}
	return result;
};

/**
 * 加密传输
 */
System.securtyD = function (source) {
	
	var keyHex = CryptoJS.enc.Utf8.parse ("Sji@zd(=HUisdO(#");
	var iv = CryptoJS.enc.Utf8.parse ("iSc(qd)J^dEd$sa<");
	var source = CryptoJS.enc.Utf8.parse (source);
	var encrypted = CryptoJS.AES.encrypt (source, keyHex, {
	    iv : iv,
	    mode : CryptoJS.mode.CBC,
	    padding : CryptoJS.pad.Iso10126
	});
	
	return CryptoJS.enc.Base64.stringify (encrypted.ciphertext);
};

// 附加类
S.D = function (source) {
	return System.securtyD (source);
}

/**
 * 基类
 * 
 * @type
 */
Base = {
    AUTHOR : "Sirius in 2014.06.09",
    MODIFIER : "Sirius in 2015.05.01",
    VERSION : "1.0",
    // 回调判断线程
    CALLBACK_THREAD : null,
    CALLBACK_NUMBER : 0,
    // CALLBACK数组
    CALLBACK_ARRAY : new Array (),
    // 路径
    PATH : new System.Map (),
    // 文件
    SCRIPT : new System.Map (),
    // 类路径
    CREATE_ARRAY : new System.Map (),
    // 使用时载入
    PREPARE_CREATE : new System.Map ()
};

/**
 * 设置路径，将类地址转换成文件地址
 * 
 * @param {}
 *            object
 */
Base.setPath = function (object) {
	if (object.path){
		for ( var props in object.path){
			this.PATH.put (props, object.path[props]);
		}
	}
}

/**
 * 导入JS，写入类
 * 
 * @param {}
 *            object
 */
Base.requires = function (object) {
	if (object.path){
		
		for ( var i = 0; i < object.path.length; i++){
			var temp = object.path[i];
			var location = temp.substring (0, temp.lastIndexOf ('.'));
			if (this.PATH.get (location) == null){
				alert ("路径设置有误：" + location);
			}
			else{
				var jsfile = temp.substring (temp.lastIndexOf ('.') + 1);
				this.CREATE_ARRAY.put (temp, jsfile);
				if (object.prepare == undefined || object.prepare == false || __debug == undefined || __debug){
					System.init.loadScript (this.PATH.get (location) + "/" + jsfile + ".js");
				}
				else{
					// 预加载模型
					this.PREPARE_CREATE.put (temp, this.PATH.get (location) + "/" + jsfile + ".js");
				}
			}
		}
		
	}
}

/**
 * 创建类，传入类名及参数
 * 
 * @param {}
 *            classname
 * @param {}
 *            args
 * @return {}
 */
Base.create = function (classname, args) {
	if (this.CREATE_ARRAY.get (classname) == null){
		alert ("找不到类：" + classname);
	}
	else{
		var func = eval (this.CREATE_ARRAY.get (classname));
		return new func (args);
		// return eval("new " + this.CREATE_ARRAY.get(classname) + "(" + args
		// + ")");
	}
};

/**
 * 预加载模型加载
 * 
 */
Base.prepareLoad = function (classnamearray, func) {
	if (__debug){
		func ();
		return;
	}
	var load = false;
	for ( var i = 0; i < classnamearray.length; i++){
		var classname = classnamearray[i];
		// 预加载是否存在
		var exists = true;
		try{
			eval (this.CREATE_ARRAY.get (classname));
		}
		catch (e){
			exists = false;
		}
		// 不存在开始加载
		if (!exists){
			var jsfile = this.PREPARE_CREATE.get (classname);
			System.init.loadScript (jsfile);
			load = true;
		}
	}
	if (load){
		// 延时半秒开始执行
		var taskload = setInterval (function () {
			if (System.running == 0){
				clearInterval (taskload);
				taskload = null;
				func ();
			}
		}, 500);
	}
	else{
		func ();
	}
	
};

/**
 * 基类，用于类定义
 * 
 * @return {}
 */
Base.Class = function () {
	// parent父类
	// config本类配置
	var len = arguments.length;
	var parent = arguments[0];
	var config = arguments[len - 1];
	
	if (config.initialize == undefined){
		config.initialize = function () {
		}
	}
	// constuct构造函数
	var construct = typeof config.initialize == "function" ? config.initialize : function () {
		var classname = eval (parent.substring (parent.lastIndexOf ('.') + 1));
		classname.prototype.initialize.apply (this, arguments);
	};
	
	if (len > 1){
		var newArgs = [
		        construct, parent
		].concat (Array.prototype.slice.call (arguments).slice (1, len - 1), config);
		// 第一个参数为construct，累加现有参数为newArgs
		try{
			// 类是否存在
			var evalclass = eval (parent.substring (parent.lastIndexOf ('.') + 1));
			Base.inherit.apply (null, newArgs);
			// // 如果类存在，判断父类是否加载完成
			// if (evalclass.__PAERNT_CLASS){
			// // [0]构造函数,[1]继承的类,[2]本类配置
			// Base.inherit.apply (null, newArgs);
			// }
			// else{
			// // 将信息放入数组
			// Base.CALLBACK_ARRAY.push ({
			// args : newArgs,
			// construct : construct
			// });
			// Base.CALLBACK_NUMBER++;
			// }
		}
		catch (e){
			var location = parent.substring (0, parent.lastIndexOf ('.'));
			var jsfile = parent.substring (parent.lastIndexOf ('.') + 1);
			var key = (Math.random () + "").substring (3);
			// 直接加载
			System.init.loadScript (Base.PATH.get (location) + "/" + jsfile + ".js", function () {
				Base.inherit.apply (null, newArgs);
			});
			
			// // 将信息放入数组
			// Base.CALLBACK_ARRAY.push ({
			// args : newArgs,
			// construct : construct
			// });
			// Base.CALLBACK_NUMBER++;
		}
		// // 查看线程是否启动
		// if (Base.CALLBACK_NUMBER != 0 && Base.CALLBACK_THREAD == null){
		// console.log ("延迟加载线程启动");
		// // 创建延迟线程
		// Base.CALLBACK_THREAD = setInterval (function () {
		// if (Base.CALLBACK_NUMBER == 0){
		// clearInterval (Base.CALLBACK_THREAD);
		// Base.CALLBACK_THREAD = null;
		// return;
		// }
		//				
		// // 获取延迟的类是否完成
		// var keyarray = Base.CALLBACK_ARRAY.length;
		// for ( var i = 0; i < keyarray; i++){
		// try{
		// // 类是否存在
		// var evalclass = eval (keyparent.substring (keyparent.lastIndexOf ('.') + 1));
		// // 类存在，查看父类是否加载完成
		// if (evalclass.__PAERNT_CLASS){
		// Base.inherit.apply (null, keyarray[i].args);
		// construct.parent =
		// Base.CALLBACK_NUMBER--;
		// }
		// else{
		// // 父类没加载
		//							
		// }
		//						
		// }
		// catch (e){
		//						
		// }
		// }
		// }, 500);
		// }
		
	}
	else{
		construct.prototype = config;
	}
	return construct;
};

/**
 * 基类，写入所有方法及变量
 * 
 * @param {}
 *            construct 构造函数
 * @param {}
 *            args 本体参数
 */
Base.inherit = function (construct, args) {
	var func = function () {
	};
	var classname = eval (args.substring (args.lastIndexOf ('.') + 1));
	func.prototype = classname.prototype;
	var newfunc = new func;
	// 继承内建变量
	if (newfunc.__proto__){
		for ( var inner in newfunc.__proto__){
			construct.prototype[inner] = newfunc.__proto__[inner];
		}
	}
	else{
		construct.prototype = newfunc;
	}
	// construct.prototype = newfunc;
	var i, l, o;
	// 除过父类开始计算
	for (i = 2, l = arguments.length; i < l; i++){
		// 最终配置文件
		config = arguments[i];
		// 最终配置文件
		if (typeof config === "function"){
			config = config.prototype;
		}
		Base.Util.extend (construct.prototype, config);
	}
	
};

/**
 * 基类，支持继承
 * 
 * @destination 父类构造函数
 * 
 * @source 本体参数
 */
Base.Util = Base.Util || {};
Base.Util.extend = function (destination, source) {
	destination = destination || {};
	if (source){
		// 本次参数全部写入父类参数中
		for ( var property in source){
			var value = source[property];
			if (value !== undefined){
				destination[property] = value;
			}
		}
		
		// 是否为整体事件
		var sourceIsEvt = typeof window.Event == "function" && source instanceof window.Event;
		
		if (!sourceIsEvt && source.hasOwnProperty && source.hasOwnProperty ("toString")){
			destination.toString = source.toString;
		}
	}
	return destination;
};

Base.com = {
    bootstrapCom : new System.Map (),
    getCmp : function (id) {
	    return Base.com.bootstrapCom.get (id);
    }
};

/**
 * 系统进度条
 */
var initial = -120;
var imageWidth = 246;
var eachPercent = (imageWidth / 2) / 100;

LoadProgress = function () {
}
LoadProgress.load = function (percentage, text) {
	try{
		var percentageWidth = eachPercent * percentage;
		var actualWidth = initial + percentageWidth;
		var eachwidth = imageWidth / 100 * percentage;
		var percentageWidth = eachPercent * percentage;
		var newProgress = eval (initial) + eval (percentageWidth) + 'px';
		document.getElementById ('_prg_bar').style.width = eachwidth + 'px';
		document.getElementById ('_prg_barText').innerHTML = percentage + "%";
		if (text){
			document.getElementById ('_prg_barTitle').innerHTML = text;
		}
	}
	catch (e){
	}
}
