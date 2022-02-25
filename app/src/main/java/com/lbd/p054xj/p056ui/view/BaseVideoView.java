package com.lbd.p054xj.p056ui.view;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import com.lbd.p054xj.C1467R;
import java.util.Timer;

/* renamed from: com.lbd.xj.ui.view.BaseVideoView */
/* loaded from: classes.dex */
public class BaseVideoView extends FrameLayout implements Animator.AnimatorListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener, View.OnClickListener, View.OnTouchListener, SeekBar.OnSeekBarChangeListener {

    /* renamed from: a */
    private final int f9947a;

    /* renamed from: b */
    private final int f9948b;

    /* renamed from: c */
    private Context f9949c;

    /* renamed from: d */
    private FrameLayout f9950d;

    /* renamed from: e */
    private MyVideoView f9951e;

    /* renamed from: f */
    private AbstractC1653a f9952f;

    /* renamed from: g */
    private int f9953g;

    /* renamed from: h */
    private int f9954h;

    /* renamed from: i */
    private Timer f9955i;

    /* renamed from: j */
    private boolean f9956j;

    /* renamed from: k */
    private boolean f9957k;

    /* renamed from: l */
    private ImageView f9958l;

    /* renamed from: m */
    private String f9959m;

    /* renamed from: com.lbd.xj.ui.view.BaseVideoView$a */
    /* loaded from: classes.dex */
    public interface AbstractC1653a {
        /* renamed from: a */
        void mo19294a();

        /* renamed from: b */
        void mo19293b();
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        return false;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    public BaseVideoView(Context context) {
        this(context, null);
        m19295b();
    }

    public BaseVideoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BaseVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f9947a = 1000;
        this.f9948b = 2000;
        this.f9954h = 5;
        this.f9955i = new Timer();
        this.f9956j = true;
        this.f9957k = false;
        this.f9949c = context;
    }

    /* renamed from: a */
    public void m19296a(String str, AbstractC1653a aVar) {
        this.f9959m = str;
        this.f9952f = aVar;
        this.f9951e.setVideoURI(Uri.parse(str));
        this.f9951e.start();
        if (getResources().getConfiguration().orientation == 1) {
            Context context = this.f9949c;
            if (context instanceof Activity) {
                ((Activity) context).setRequestedOrientation(0);
            }
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        m19295b();
    }

    /* renamed from: b */
    private void m19295b() {
        View inflate = LayoutInflater.from(this.f9949c).inflate(C1467R.layout.base_video_view, (ViewGroup) null);
        this.f9950d = (FrameLayout) inflate.findViewById(C1467R.C1469id.viewBox);
        this.f9958l = (ImageView) inflate.findViewById(C1467R.C1469id.closeDialog);
        this.f9951e = (MyVideoView) inflate.findViewById(C1467R.C1469id.videoView);
        this.f9951e.setOnPreparedListener(this);
        this.f9951e.setOnCompletionListener(this);
        this.f9951e.setOnErrorListener(this);
        this.f9950d.setOnTouchListener(this);
        this.f9950d.setOnClickListener(this);
        this.f9958l.setOnClickListener(this);
        addView(inflate);
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        this.f9953g = this.f9951e.getDuration();
        m19297a(this.f9953g);
        mediaPlayer.start();
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        this.f9951e.seekTo(0);
        this.f9952f.mo19293b();
    }

    /* renamed from: a */
    private int[] m19297a(int i) {
        int i2 = i / 1000;
        return new int[]{i2 / 60, i2 % 60};
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AbstractC1653a aVar;
        if (view.getId() == C1467R.C1469id.closeDialog && (aVar = this.f9952f) != null) {
            aVar.mo19294a();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        this.f9957k = false;
        this.f9956j = !this.f9956j;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
        this.f9951e.pause();
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        this.f9951e.start();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m19298a();
    }

    /* renamed from: a */
    public void m19298a() {
        this.f9955i.cancel();
        this.f9955i = null;
        this.f9951e.stopPlayback();
    }
}
